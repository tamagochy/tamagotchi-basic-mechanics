package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.ActionDao;
import ru.tamagotchi.basicmechanics.dao.PetDao;
import ru.tamagotchi.basicmechanics.domain.Action;
import ru.tamagotchi.basicmechanics.domain.ActionCode;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.exception.*;
import ru.tamagotchi.basicmechanics.service.api.AuthService;
import ru.tamagotchi.basicmechanics.service.api.PetService;
import ru.tamagotchi.basicmechanics.service.api.ScheduleService;

import java.util.Arrays;
import java.util.Random;

import static java.time.LocalDateTime.now;
import static ru.tamagotchi.basicmechanics.domain.Pet.INDICATOR_MAX_VALUE;
import static ru.tamagotchi.basicmechanics.domain.PetStatus.*;

/**
 * Created by makar
 * 02.10.2018 18:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final ScheduleService scheduleService;
    private final AuthService authService;
    private final PetDao petDao;
    private final ActionDao actionDao;

    private Random random = new Random();

    @Override
    public Pet getCurrent() {
        Long totalPets = petDao.countAllByOwnerId(authService.getCurrentUserId());
        if (totalPets == 0) {
            throw new PetNotExistsException();
        }
        Pet pet = petDao.getAllByOwnerIdAndStatusIn(authService.getCurrentUserId(), Arrays.asList(ACTIVE, SLEEP))
                .stream()
                .findFirst()
                .orElseThrow(PetNotFoundException::new);
        return applySchedule(pet);
    }

    @Override
    public Pet create(String name) {
        petDao.getAllByOwnerIdAndStatusIn(authService.getCurrentUserId(), Arrays.asList(ACTIVE, SLEEP))
                .stream()
                .findAny()
                .ifPresent(pet -> {
                    throw new PetAlreadyExistException();
                });
        Pet pet = new Pet(name, authService.getCurrentUserId(), now());
        switch (random.nextInt(3)) {
            case 0:
                pet.decreaseHealth();
                break;
            case 1:
                pet.decreaseHunger();
                break;
            case 2:
                pet.decreaseMood();
                break;
        }

        return petDao.save(pet);
    }

    @Override
    public Pet feed() {
        Pet pet = getCurrent();
        assertNotSleep(pet);
        checkIndicator("hunger", pet.getHunger());
        pet.increaseHunger();
        return petDao.save(pet);
    }

    @Override
    public Pet sleep() {
        Pet pet = getCurrent();
        assertNotSleep(pet);
        checkIndicator("rest", pet.getRest());
        pet.increaseRest();
        pet.setStatus(SLEEP);
        return petDao.save(pet);
    }

    @Override
    public Pet treat() {
        Pet pet = getCurrent();
        assertNotSleep(pet);
        checkIndicator("health", pet.getHealth());
        pet.increaseHealth();
        return petDao.save(pet);
    }

    @Override
    public Pet play(ActionCode code) {
        Action action = actionDao.getByCode(code);
        Pet pet = getCurrent();
        assertNotSleep(pet);
        checkIndicator("mood", pet.getMood());
        pet.increaseMood(action.getValue());
        return petDao.save(pet);
    }

    private void assertNotSleep(Pet pet) {
        if (pet.getStatus() == SLEEP) {
            throw new PetNotAvailableException();
        }
    }

    private Pet applySchedule(Pet pet) {
        boolean changed = scheduleService.applySchedule(pet, now());
        if (changed) {
            log.debug("pet updated after applying schedule: {}", pet);
            pet.setLastScheduleApplyTime(now());
            petDao.save(pet);
        }
        if (pet.getStatus() == LEAVE) {
            throw new PetNotFoundException();
        }
        return pet;
    }

    private void checkIndicator(String name, Integer value) {
        log.debug("check indicator '{}', current value = {}", name, value);
        if (value == INDICATOR_MAX_VALUE) {
            throw new IndicatorFullException(name);
        }
    }
}
