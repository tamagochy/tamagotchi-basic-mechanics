package ru.tamagotchi.basicmechanics.service.api;

import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.domain.ScheduleItem;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by makar
 * 07.10.2018 18:07
 */
public interface ScheduleService {
    List<ScheduleItem> getSchedule();

    void applySchedule(Pet pet, LocalDateTime currentTime);
}
