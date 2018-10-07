package ru.tamagotchi.basicmechanics.service.api;

import ru.tamagotchi.basicmechanics.domain.Pet;

import java.time.LocalDateTime;

/**
 * Created by makar
 * 07.10.2018 18:07
 */
public interface ScheduleService {
    void applySchedule(Pet pet, LocalDateTime currentTime);
}
