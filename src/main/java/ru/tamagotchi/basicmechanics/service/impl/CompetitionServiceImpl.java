package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.client.CompetitionClient;
import ru.tamagotchi.basicmechanics.domain.ActionCode;
import ru.tamagotchi.basicmechanics.domain.DiseaseCode;
import ru.tamagotchi.basicmechanics.domain.RoomCode;
import ru.tamagotchi.basicmechanics.dto.ChangeScoreRequest;
import ru.tamagotchi.basicmechanics.service.api.CompetitionService;

import java.time.LocalTime;

/**
 * Created by makar
 * 18.12.2018 1:39
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionClient client;

    @Override
    public Integer currentScore() {
        return client.currentScore().getData();
    }

    @Override
    public void changeScore(ActionCode action, RoomCode room, DiseaseCode disease) {
        try {
            ChangeScoreRequest request = new ChangeScoreRequest(action, room, disease, LocalTime.now());
            log.info("change score request: {}", request);
            client.changeScore(request);
            log.info("change score request successful");
        } catch (Exception e) {
            log.error("error while change score request", e);
        }
    }
}
