package ru.tamagotchi.basicmechanics.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by makar
 * 07.10.2018 10:18
 */
@Data
@ApiModel("Pet")
public class PetDto {
    private String name;

    @ApiModelProperty(allowableValues = "range[25, 100]")
    private Integer health;

    @ApiModelProperty(allowableValues = "range[25, 100]")
    private Integer hunger;

    @ApiModelProperty(allowableValues = "range[25, 100]")
    private Integer rest;

    @ApiModelProperty(allowableValues = "range[25, 100]")
    private Integer mood;

    private String disease;

    private boolean active;
}
