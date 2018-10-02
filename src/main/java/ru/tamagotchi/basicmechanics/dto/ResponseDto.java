package ru.tamagotchi.basicmechanics.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * Created by makar
 * 02.10.2018 22:44
 */
@Data
public class ResponseDto<T> {
    private T data;
    private List<ErrorDto> errors;

    public ResponseDto(T data) {
        this.data = data;
    }

    public ResponseDto(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public static ResponseDto withSingleError(ErrorDto error) {
        return new ResponseDto(Collections.singletonList(error));
    }
}
