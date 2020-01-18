package ru.tamagotchi.basicmechanics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.dto.ScheduleItemDto;
import ru.tamagotchi.basicmechanics.mapper.ScheduleMapper;
import ru.tamagotchi.basicmechanics.service.api.ScheduleService;

import java.util.List;

/**
 * Created by makar
 * 07.10.2018 18:33
 */
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;
    private final ScheduleMapper mapper;

    @GetMapping
    public ResponseDto<List<ScheduleItemDto>> getSchedule() {
        return new ResponseDto<>(mapper.toDtoList(service.getSchedule()));
    }
}
