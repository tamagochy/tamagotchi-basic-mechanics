package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.PetDao;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.exception.PetNotExistsException;
import ru.tamagotchi.basicmechanics.exception.PetNotFoundException;
import ru.tamagotchi.basicmechanics.exception.handler.IndexFullException;
import ru.tamagotchi.basicmechanics.service.api.PetService;

import static java.time.LocalDateTime.now;
import static ru.tamagotchi.basicmechanics.domain.Pet.INDEX_MAX_VALUE;
import static ru.tamagotchi.basicmechanics.domain.PetStatus.ACTIVE;

/**
 * Created by makar
 * 02.10.2018 18:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetDao petDao;

    @Override
    public Pet getCurrent() {
        Long totalPets = petDao.countAllByOwnerId(42);
        if (totalPets == 0) {
            throw new PetNotExistsException();
        }
        return petDao.getAllByOwnerIdAndStatus(42, ACTIVE)
                .stream()
                .findFirst()
                .orElseThrow(PetNotFoundException::new);
    }

    @Override
    public Pet create() {
        Pet pet = new Pet(42, INDEX_MAX_VALUE, INDEX_MAX_VALUE, INDEX_MAX_VALUE, INDEX_MAX_VALUE, now(), now(), ACTIVE);
        return petDao.save(pet);
    }

    @Override
    public Pet feed() {
        Pet pet = getCurrent();
        checkIndex("hunger", pet.getHunger());
        pet.increaseHunger();
        return petDao.save(pet);
    }

    @Override
    public Pet sleep() {
        Pet pet = getCurrent();
        checkIndex("rest", pet.getRest());
        pet.increaseRest();
        return petDao.save(pet);
    }

    @Override
    public Pet treat() {
        Pet pet = getCurrent();
        checkIndex("health", pet.getHealth());
        pet.increaseHealth();
        return petDao.save(pet);
    }

    @Override
    public Pet play() {
        Pet pet = getCurrent();
        checkIndex("mood", pet.getMood());
        pet.increaseMood();
        return petDao.save(pet);
    }

    private void checkIndex(String name, Integer value) {
        if (value == INDEX_MAX_VALUE) {
            throw new IndexFullException(name);
        }
    }
}
