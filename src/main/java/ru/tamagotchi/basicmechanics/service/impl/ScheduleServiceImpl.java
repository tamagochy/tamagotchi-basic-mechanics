package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.ScheduleItemDao;
import ru.tamagotchi.basicmechanics.domain.DiseaseCode;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.domain.PetStatus;
import ru.tamagotchi.basicmechanics.domain.ScheduleItem;
import ru.tamagotchi.basicmechanics.service.api.ScheduleService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Collections.unmodifiableList;

/**
 * Created by makar
 * 07.10.2018 13:52
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleItemDao dao;

    private List<ScheduleItem> schedule;
    private Random random = new Random();

    @PostConstruct
    public void init() {
        schedule = unmodifiableList(dao.findAll());
    }

    @Override
    public List<ScheduleItem> getSchedule() {
        return schedule.stream().filter(scheduleItem -> !scheduleItem.isDecreaseMood()).collect(Collectors.toList());
    }

    @Override
    public boolean applySchedule(Pet pet, LocalDateTime currentDateTime) {
        log.debug("try to apply schedule to pet {}", pet);
        if (pet == null) {
            return false;
        }

        if (DAYS.between(pet.getLastHealthIncreaseTime(), currentDateTime) >= 1) {
            log.debug("pet leave because health has critical value");
            pet.leave();
            return true;
        }
        if (DAYS.between(pet.getLastHungerIncreaseTime(), currentDateTime) >= 1) {
            log.debug("pet leave because hunger has critical value");
            pet.leave();
            return true;
        }
        if (DAYS.between(pet.getLastRestIncreaseTime(), currentDateTime) >= 1) {
            log.debug("pet leave because rest has critical value");
            pet.leave();
            return true;
        }
        if (DAYS.between(pet.getLastMoodIncreaseTime(), currentDateTime) >= 1) {
            log.debug("pet leave because mood has critical value");
            pet.leave();
            return true;
        }

        log.debug("searching events for pet...");
        Set<ScheduleItem> events = new LinkedHashSet<>();

        LocalDateTime lastScheduleApplyDateTime = pet.getLastScheduleApplyTime();
        LocalTime lastScheduleApplyTime = lastScheduleApplyDateTime.toLocalTime();
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (lastScheduleApplyDateTime.toLocalDate().equals(currentDateTime.toLocalDate())) {
            filterSchedule(events, lastScheduleApplyTime, currentTime);
        } else {
            LocalTime startOfDay = LocalTime.of(0, 0);
            LocalTime endOfDay = LocalTime.of(23, 59);
            filterSchedule(events, lastScheduleApplyTime, endOfDay);
            filterSchedule(events, startOfDay, currentTime);
        }

        if (events.isEmpty()) {
            log.debug("events not found");
            return false;
        }
        boolean changed = false;
        for (ScheduleItem event : events) {
            log.debug("event found: {}", event);
            changed |= applyScheduleItem(pet, event);
        }
        return changed;
    }

    private void filterSchedule(Set<ScheduleItem> events, LocalTime start, LocalTime end) {
        schedule.stream()
                .filter(e -> e.getTime().isAfter(start))
                .filter(e -> e.getTime().isBefore(end))
                .forEach(events::add);
    }

    private boolean applyScheduleItem(Pet pet, ScheduleItem item) {
        boolean changed = false;
        if (item.isBeginningOfDay()) {
            if (pet.getStatus() == PetStatus.SLEEP) {
                pet.setStatus(PetStatus.ACTIVE);
                changed = true;
            }
            if (random.nextDouble() > 0.5) {
                pet.decreaseHealth(Pet.INDICATOR_MAX_VALUE);
                pet.setDiseaseCode(DiseaseCode.random());
                changed = true;
            }
        }
        if (item.isDecreaseHunger()) {
            pet.decreaseHunger();
            changed = true;
        }
        if (item.isDecreaseMood()) {
            pet.decreaseMood();
            changed = true;
        }
        if (item.isDecreaseRest()) {
            pet.decreaseRest();
            changed = true;
        }
        return changed;
    }
}
