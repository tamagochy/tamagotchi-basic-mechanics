package ru.tamagotchi.basicmechanics.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.ScheduleItemDao;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.domain.PetStatus;
import ru.tamagotchi.basicmechanics.domain.ScheduleItem;
import ru.tamagotchi.basicmechanics.service.api.ScheduleService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
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
    public void applySchedule(Pet pet, LocalDateTime currentTime) {
        if (pet == null) {
            return;
        }

        LocalDateTime lastAccessTime = pet.getLastAccessTime();

        if (!lastAccessTime.toLocalDate().equals(currentTime.toLocalDate()) && pet.hasCriticalIndicator()) {
            pet.setStatus(PetStatus.LEAVE);
            return;
        }
        schedule.stream()
                .filter(i -> i.getTime().isAfter(lastAccessTime.toLocalTime()))
                .filter(i -> i.getTime().isBefore(currentTime.toLocalTime()))
                .forEach(i -> applyScheduleItem(pet, i));
    }

    private void applyScheduleItem(Pet pet, ScheduleItem item) {
        if (item.isBeginningOfDay()) {
            if (pet.getStatus() == PetStatus.SLEEP) {
                pet.setStatus(PetStatus.ACTIVE);
            }
            if (random.nextDouble() > 0.5) {
                pet.decreaseHealth();
            }
        }
        if (item.isDecreaseHunger()) {
            pet.decreaseHunger();
        }
        if (item.isDecreaseMood()) {
            pet.decreaseMood();
        }
        if (item.isDecreaseRest()) {
            pet.decreaseRest();
        }
    }
}
