package ru.tamagotchi.basicmechanics.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.tamagotchi.basicmechanics.validation.annotation.constraint.ValidActionCode;
import ru.tamagotchi.basicmechanics.validation.annotation.constraint.ValidRoomCode;

/**
 * Created by makar
 * 07.10.2018 21:17
 */
@Data
@ApiModel("ActionRequest")
public class ActionRequestDto {
    @ValidActionCode
    @ApiModelProperty(dataType = "ru.tamagotchi.basicmechanics.domain.ActionCode")
    private String action;

    @ValidRoomCode
    @ApiModelProperty(dataType = "ru.tamagotchi.basicmechanics.domain.RoomCode")
    private String room;
}
