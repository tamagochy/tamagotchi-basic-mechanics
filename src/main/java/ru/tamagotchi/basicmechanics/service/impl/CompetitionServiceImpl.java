package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.client.CompetitionClient;
import ru.tamagotchi.basicmechanics.service.api.CompetitionService;

/**
 * Created by makar
 * 18.12.2018 1:39
 */
@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionClient client;

    @Override
    public Integer currentScore() {
        return client.currentScore().getData();
    }
}
