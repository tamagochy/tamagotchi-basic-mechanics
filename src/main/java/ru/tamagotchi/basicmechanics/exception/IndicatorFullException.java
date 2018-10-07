package ru.tamagotchi.basicmechanics.exception;

import lombok.Getter;

/**
 * Created by makar
 * 07.10.2018 12:07
 */
@Getter
public class IndicatorFullException extends RuntimeException {
    private String indicatorName;

    public IndicatorFullException(String indicatorName) {
        this.indicatorName = indicatorName;
    }
}
