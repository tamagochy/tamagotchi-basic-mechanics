package ru.tamagotchi.basicmechanics.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by makar
 * 09.10.2018 18:35
 */
@Data
@ApiModel("Action")
public class ActionDto {
    private String code;
    private String description;
    private Integer value;
}
