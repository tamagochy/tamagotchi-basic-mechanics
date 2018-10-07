package ru.tamagotchi.basicmechanics.controller;


import lombok.RequiredArgsConstructor;
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
    public ResponseDto<PetDto> myPet() {
        return new ResponseDto<>(mapper.toDto(petService.getCurrentUserPet()));
    }

    @PostMapping
    @ResponseBody
    public ResponseDto<PetDto> create() {
        return new ResponseDto<>(mapper.toDto(petService.createNew()));
    }
}
