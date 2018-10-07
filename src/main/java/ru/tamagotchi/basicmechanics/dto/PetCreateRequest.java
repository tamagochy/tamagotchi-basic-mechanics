package ru.tamagotchi.basicmechanics.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by makar
 * 07.10.2018 19:05
 */
@Data
public class PetCreateRequest {
    @NotBlank
    @Size(max = 100)
    private String name;
}
