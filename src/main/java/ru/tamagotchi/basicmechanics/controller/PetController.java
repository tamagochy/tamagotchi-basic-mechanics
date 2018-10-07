package ru.tamagotchi.basicmechanics.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tamagotchi.basicmechanics.dto.PetDto;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.mapper.PetMapper;
import ru.tamagotchi.basicmechanics.service.api.PetService;

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
    public ResponseDto<PetDto> create() {
        return new ResponseDto<>(mapper.toDto(petService.create()));
    }

    @PutMapping(value = "/feed")
    @ResponseBody
    public ResponseDto<PetDto> feed() {
        return new ResponseDto<>(mapper.toDto(petService.feed()));
    }

    @PutMapping(value = "/sleep")
    @ResponseBody
    public ResponseDto<PetDto> sleep() {
        return new ResponseDto<>(mapper.toDto(petService.sleep()));
    }

    @PutMapping(value = "/treat")
    @ResponseBody
    public ResponseDto<PetDto> treat() {
        return new ResponseDto<>(mapper.toDto(petService.treat()));
    }

    @PutMapping(value = "/play")
    @ResponseBody
    public ResponseDto<PetDto> play() {
        return new ResponseDto<>(mapper.toDto(petService.play()));
    }
}
