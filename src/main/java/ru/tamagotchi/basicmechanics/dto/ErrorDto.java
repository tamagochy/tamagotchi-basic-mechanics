package ru.tamagotchi.basicmechanics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by makar
 * 02.10.2018 19:13
 */
@Data
@AllArgsConstructor
@ApiModel(value = "Error")
public class ErrorDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attr;
    private String code;

    public ErrorDto(String code) {
        this.code = code;
    }
}
