package ru.tamagotchi.basicmechanics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.tamagotchi.basicmechanics.dto.ChangeScoreRequest;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;

/**
 * Created by makar
 * 18.12.2018 1:34
 */
@FeignClient(name = "competition", url = "${competition.url}")
public interface CompetitionClient {
    @GetMapping("/getScore")
    ResponseDto<Integer> currentScore();

    @PutMapping("/changeScore")
    void changeScore(ChangeScoreRequest request);
}
