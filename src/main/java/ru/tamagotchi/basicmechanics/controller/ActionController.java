package ru.tamagotchi.basicmechanics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tamagotchi.basicmechanics.dto.ActionDto;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.mapper.ActionMapper;
import ru.tamagotchi.basicmechanics.service.api.ActionService;

import java.util.List;

/**
 * Created by makar
 * 09.10.2018 18:43
 */
@RestController
@RequestMapping("/actions")
@RequiredArgsConstructor
public class ActionController {
    private final ActionService service;
    private final ActionMapper mapper;

    @GetMapping
    public ResponseDto<List<ActionDto>> getAll() {
        return new ResponseDto<>(mapper.toDtoList(service.getActions()));
    }
}
