package ru.tamagotchi.basicmechanics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tamagotchi.basicmechanics.domain.ActionCode;
import ru.tamagotchi.basicmechanics.domain.DiseaseCode;
import ru.tamagotchi.basicmechanics.domain.RoomCode;

import java.time.LocalTime;

/**
 * Created by makar
 * 18.12.2018 2:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeScoreRequest {
    private ActionCode actionCode;
    private RoomCode roomCode;
    private DiseaseCode deseaseCode;
    private LocalTime time;
}
