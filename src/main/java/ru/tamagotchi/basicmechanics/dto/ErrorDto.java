package ru.tamagotchi.basicmechanics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by makar
 * 02.10.2018 19:13
 */
@Data
@AllArgsConstructor
public class ErrorDto {
    private String attr;
    private String code;

    public ErrorDto(String code) {
        this.code = code;
    }
}
