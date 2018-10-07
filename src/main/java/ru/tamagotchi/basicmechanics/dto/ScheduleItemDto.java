package ru.tamagotchi.basicmechanics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * Created by makar
 * 07.10.2018 17:03
 */
@Data
@NoArgsConstructor
@ApiModel("ScheduleItem")
public class ScheduleItemDto {
    @JsonFormat(pattern = "HH:mm")
    @ApiModelProperty(dataType = "string")
    private LocalTime time;

    private String description;
}
