package ru.tamagotchi.basicmechanics.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tamagotchi.basicmechanics.dto.ActionRequestDto;
import ru.tamagotchi.basicmechanics.dto.PetCreateRequestDto;
import ru.tamagotchi.basicmechanics.dto.PetDto;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.mapper.PetMapper;
import ru.tamagotchi.basicmechanics.service.ActionRequest;
import ru.tamagotchi.basicmechanics.service.api.PetService;

import javax.validation.Valid;

/**
 * Created by makar
 * 02.10.2018 18:17
 */
@RestController
@RequestMapping(value = "/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;
    private final PetMapper mapper;

    @GetMapping
    public ResponseDto<PetDto> current() {
        return new ResponseDto<>(mapper.toDto(petService.getCurrent()));
    }

    @PostMapping(value = "/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<PetDto> create(@Valid @RequestBody PetCreateRequestDto request) {
        return new ResponseDto<>(mapper.toDto(petService.create(request.getName())));
    }

    @PutMapping(value = "/feed")
    @ResponseBody
    public ResponseDto<PetDto> feed(@Valid @RequestBody ActionRequestDto request) {
        return new ResponseDto<>(mapper.toDto(petService.feed(new ActionRequest(request))));
    }

    @PutMapping(value = "/sleep")
    @ResponseBody
    public ResponseDto<PetDto> sleep() {
        return new ResponseDto<>(mapper.toDto(petService.sleep()));
    }

    @PutMapping(value = "/treat")
    @ResponseBody
    public ResponseDto<PetDto> treat(@Valid @RequestBody ActionRequestDto request) {
        return new ResponseDto<>(mapper.toDto(petService.treat(new ActionRequest(request))));
    }

    @PutMapping(value = "/play")
    @ResponseBody
    public ResponseDto<PetDto> play(@Valid @RequestBody ActionRequestDto request) {
        return new ResponseDto<>(mapper.toDto(petService.play(new ActionRequest(request))));
    }
}
