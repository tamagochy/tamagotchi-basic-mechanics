package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.ActionDao;
import ru.tamagotchi.basicmechanics.dao.PetDao;
import ru.tamagotchi.basicmechanics.domain.Action;
import ru.tamagotchi.basicmechanics.domain.Indicator;
import ru.tamagotchi.basicmechanics.domain.IndicatorCode;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.exception.*;
import ru.tamagotchi.basicmechanics.service.ActionRequest;
import ru.tamagotchi.basicmechanics.service.api.AuthService;
import ru.tamagotchi.basicmechanics.service.api.CompetitionService;
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
    private final CompetitionService competitionService;
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
        fillScore(pet);
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
                pet.decreaseHealth(INDICATOR_MAX_VALUE);
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
    public Pet feed(ActionRequest actionRequest) {
        Action action = resolveAction(actionRequest, IndicatorCode.hunger);
        Pet pet = getCurrent();
        assertNotSleep(pet);
        checkIndicator(IndicatorCode.hunger, pet.getHunger());
        pet.increaseHunger(action.getValue1());
        if (action.getAdditionalIndicator() != null) {
            pet.decreaseIndicator(action.getAdditionalIndicator(), -action.getValue2());
        }
        return petDao.save(pet);
    }

    @Override
    public Pet sleep() {
        Pet pet = getCurrent();
        assertNotSleep(pet);
        checkIndicator(IndicatorCode.rest, pet.getRest());
        pet.increaseRest();
        pet.setStatus(SLEEP);
        return petDao.save(pet);
    }

    @Override
    public Pet treat(ActionRequest actionRequest) {
        Action action = resolveAction(actionRequest, IndicatorCode.health);
        Pet pet = getCurrent();
        assertNotSleep(pet);
        checkIndicator(IndicatorCode.health, pet.getHealth());
        pet.increaseHealth(action.getValue1());
        if (pet.getDiseaseCode() != null && pet.getDiseaseCode() == action.getDiseaseCode()) {
            log.info("disease is cured");
            pet.setDiseaseCode(null);
        }
        return petDao.save(pet);
    }

    @Override
    public Pet play(ActionRequest actionRequest) {
        Action action = resolveAction(actionRequest, IndicatorCode.mood);
        Pet pet = getCurrent();
        assertNotSleep(pet);
        checkIndicator(IndicatorCode.mood, pet.getMood());
        pet.increaseMood(action.getValue1());
        return petDao.save(pet);
    }

    private void fillScore(Pet pet) {
        log.info("getting current score");
        try {
            Integer score = competitionService.currentScore();
            log.info("current score: {}", score);
            pet.setScore(score);
        } catch (Exception e) {
            log.error("error while make request to competition microservice", e);
        }
    }

    private Action resolveAction(ActionRequest actionRequest, IndicatorCode code) {
        Action action = actionDao.getByCode(actionRequest.getActionCode());
        Indicator indicator = action.getMainIndicator();
        if (!indicator.accepted(code, actionRequest.getRoomCode())) {
            throw new NotAcceptedRoomException();
        }
        return action;
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

    private void checkIndicator(IndicatorCode indicator, Integer value) {
        log.debug("check indicator '{}', current value = {}", indicator, value);
        if (value == INDICATOR_MAX_VALUE) {
            throw new IndicatorFullException();
        }
    }
}
