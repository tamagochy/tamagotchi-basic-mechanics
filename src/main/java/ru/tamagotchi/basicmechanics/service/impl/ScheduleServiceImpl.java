package ru.tamagotchi.basicmechanics.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.ScheduleItemDao;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.domain.PetStatus;
import ru.tamagotchi.basicmechanics.domain.ScheduleItem;
import ru.tamagotchi.basicmechanics.service.api.ScheduleService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

/**
 * Created by makar
 * 07.10.2018 13:52
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleItemDao dao;

    @Getter
    private List<ScheduleItem> schedule;
    private Random random = new Random();

    @PostConstruct
    public void init() {
        schedule = unmodifiableList(stream(dao.findAll().spliterator(), false).collect(toList()));
    }

    @Override
    public boolean applySchedule(Pet pet, LocalDateTime currentTime) {
        log.debug("try to apply schedule to pet {}", pet);
        if (pet == null) {
            return false;
        }

        LocalDateTime lastAccessDateTime = pet.getLastAccessTime();

        if (!lastAccessDateTime.toLocalDate().equals(currentTime.toLocalDate())) {
            lastAccessDateTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
            if (pet.hasCriticalIndicator()) {
                log.debug("pet leave");
                pet.setStatus(PetStatus.LEAVE);
                return true;
            }
        }
        log.debug("search events for pet");
        LocalTime lastAccessTime = lastAccessDateTime.toLocalTime();
        List<ScheduleItem> events = schedule.stream()
                .filter(e -> e.getTime().isAfter(lastAccessTime))
                .filter(e -> e.getTime().isBefore(currentTime.toLocalTime()))
                .collect(toList());
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

    private boolean applyScheduleItem(Pet pet, ScheduleItem item) {
        boolean changed = false;
        if (item.isBeginningOfDay()) {
            if (pet.getStatus() == PetStatus.SLEEP) {
                pet.setStatus(PetStatus.ACTIVE);
                changed = true;
            }
            if (random.nextDouble() > 0.5) {
                pet.decreaseHealth();
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
