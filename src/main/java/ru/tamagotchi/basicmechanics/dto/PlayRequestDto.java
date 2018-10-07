package ru.tamagotchi.basicmechanics.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.tamagotchi.basicmechanics.domain.ActionCode;

import javax.validation.constraints.NotNull;

/**
 * Created by makar
 * 07.10.2018 21:17
 */
@Data
@ApiModel("PlayRequest")
public class PlayRequestDto {
    @NotNull
    private ActionCode action;
}
