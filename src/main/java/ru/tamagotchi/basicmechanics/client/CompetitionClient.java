package ru.tamagotchi.basicmechanics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;

/**
 * Created by makar
 * 18.12.2018 1:34
 */
@FeignClient(name = "competition", url = "${competition.url}")
public interface CompetitionClient {
    @GetMapping("/getScore")
    ResponseDto<Integer> currentScore();
}
