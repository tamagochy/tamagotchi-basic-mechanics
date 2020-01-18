package ru.tamagotchi.basicmechanics.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * Created by makar
 * 02.10.2018 22:44
 */
@Data
@ApiModel("Response")
public class ResponseDto<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorDto> errors;

    public ResponseDto(T data) {
        this.data = data;
    }

    public ResponseDto(ErrorDto error) {
        this.errors = Collections.singletonList(error);
    }

    public ResponseDto(List<ErrorDto> errors) {
        this.errors = errors;
    }
}
