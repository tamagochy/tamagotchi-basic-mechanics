package ru.tamagotchi.basicmechanics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ru.tamagotchi.basicmechanics.exception.handler.ErrorCode;

/**
 * Created by makar
 * 02.10.2018 19:13
 */
@Data
@ApiModel(value = "Error")
public class ErrorDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String attr;
    private String code;

    public ErrorDto(ErrorCode code) {
        this.code = code.getCode();
    }

    public ErrorDto(String attr, ErrorCode code) {
        this.attr = attr;
        this.code = code.getCode();
    }
}
