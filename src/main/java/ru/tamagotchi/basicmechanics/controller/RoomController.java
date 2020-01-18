package ru.tamagotchi.basicmechanics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.service.api.RoomService;

import java.util.List;

/**
 * Created by makar
 * 17.12.2018 23:56
 */
@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping
    public ResponseDto<List<String>> all() {
        return new ResponseDto<>(service.roomCodes());
    }
}
