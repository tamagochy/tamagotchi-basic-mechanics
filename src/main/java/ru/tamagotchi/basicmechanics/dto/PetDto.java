package ru.tamagotchi.basicmechanics.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by makar
 * 07.10.2018 10:18
 */
@Data
@ApiModel("Pet")
public class PetDto {
    private Integer health;
    private Integer hunger;
    private Integer rest;
    private Integer mood;
}
