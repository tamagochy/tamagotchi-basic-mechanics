package ru.tamagotchi.basicmechanics.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tamagotchi.basicmechanics.domain.Pet;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
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

    @GetMapping
    public ResponseDto<Pet> myPet() {
        return new ResponseDto<>(petService.getCurrentUserPet());
    }

    @PostMapping
    @ResponseBody
    public ResponseDto<Pet> create() {
        return new ResponseDto<>(petService.createNew());
    }
}
