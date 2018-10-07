package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.PetDao;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.exception.*;
import ru.tamagotchi.basicmechanics.service.api.PetService;
import ru.tamagotchi.basicmechanics.service.api.ScheduleService;

import java.util.Arrays;

import static java.time.LocalDateTime.now;
import static ru.tamagotchi.basicmechanics.domain.Pet.INDICATOR_MAX_VALUE;
import static ru.tamagotchi.basicmechanics.domain.PetStatus.ACTIVE;
import static ru.tamagotchi.basicmechanics.domain.PetStatus.SLEEP;

/**
 * Created by makar
 * 02.10.2018 18:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final ScheduleService scheduleService;
    private final PetDao petDao;

    @Override
    public Pet getCurrent() {
        Long totalPets = petDao.countAllByOwnerId(42);
        if (totalPets == 0) {
            throw new PetNotExistsException();
        }
        return petDao.getAllByOwnerIdAndStatusIn(42, Arrays.asList(ACTIVE, SLEEP))
                .stream()
                .findFirst()
                .orElseThrow(PetNotFoundException::new);
    }

    @Override
    public Pet create(String name) {
        petDao.getAllByOwnerIdAndStatusIn(42, Arrays.asList(ACTIVE, SLEEP))
                .stream()
                .findAny()
                .ifPresent(pet -> {
                    throw new PetAlreadyExistException();
                });
        Pet pet = new Pet(
                name,
                42,
                INDICATOR_MAX_VALUE,
                INDICATOR_MAX_VALUE,
                INDICATOR_MAX_VALUE,
                INDICATOR_MAX_VALUE,
                now(),
                now(),
                ACTIVE
        );
        return petDao.save(pet);
    }

    @Override
    public Pet feed() {
        Pet pet = getCurrent();
        checkPetStatus(pet);
        applySchedule(pet);
        checkIndicator("hunger", pet.getHunger());
        pet.increaseHunger();
        pet.setLastAccessTime(now());
        return petDao.save(pet);
    }

    @Override
    public Pet sleep() {
        Pet pet = getCurrent();
        checkPetStatus(pet);
        applySchedule(pet);
        checkIndicator("rest", pet.getRest());
        pet.increaseRest();
        pet.setLastAccessTime(now());
        pet.setStatus(SLEEP);
        return petDao.save(pet);
    }

    @Override
    public Pet treat() {
        Pet pet = getCurrent();
        checkPetStatus(pet);
        applySchedule(pet);
        checkIndicator("health", pet.getHealth());
        pet.increaseHealth();
        pet.setLastAccessTime(now());
        return petDao.save(pet);
    }

    @Override
    public Pet play() {
        Pet pet = getCurrent();
        checkPetStatus(pet);
        applySchedule(pet);
        checkIndicator("mood", pet.getMood());
        pet.increaseMood();
        pet.setLastAccessTime(now());
        return petDao.save(pet);
    }

    private void checkPetStatus(Pet pet) {
        if (pet.getStatus() == SLEEP) {
            throw new PetNotAvailableException();
        }
    }

    private void applySchedule(Pet pet) {
        scheduleService.applySchedule(pet, now());
        if (pet.getStatus() != ACTIVE) {
            petDao.save(pet);
            throw new PetNotFoundException();
        }
    }


    private void checkIndicator(String name, Integer value) {
        if (value == INDICATOR_MAX_VALUE) {
            throw new IndicatorFullException(name);
        }
    }
}
