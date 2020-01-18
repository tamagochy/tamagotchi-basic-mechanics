package ru.tamagotchi.basicmechanics.service.api;

import ru.tamagotchi.basicmechanics.domain.ActionCode;
import ru.tamagotchi.basicmechanics.domain.DiseaseCode;
import ru.tamagotchi.basicmechanics.domain.RoomCode;

/**
 * Created by makar
 * 18.12.2018 1:39
 */
public interface CompetitionService {
    Integer currentScore();
    void changeScore(ActionCode action, RoomCode room, DiseaseCode disease);
}
