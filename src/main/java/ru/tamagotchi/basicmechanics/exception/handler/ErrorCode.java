package ru.tamagotchi.basicmechanics.exception.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by makar
 * 11.10.2018 22:17
 */
@RequiredArgsConstructor
public enum ErrorCode {
    SERVER_ERROR("server.Error"),
    UNAUTHORIZED("https://i.ytimg.com/vi/qkudeorV03o/maxresdefault.jpg"),
    URL_NOT_FOUND("url.NotFound"),
    PROTOCOL_INCORRECT("protocol.Incorrect"),
    VALIDATION_MISSING("validation.Missing"),
    VALIDATION_INCORRECT("validation.Incorrect"),
    BUSINESS_PET_ALREADY_EXIST("business.PetAlreadyExist"),
    BUSINESS_PET_NOT_AVAILABLE("business.PetNotAvailable"),
    BUSINESS_PET_NOT_FOUND("business.PetNotFound"),
    BUSINESS_PET_NOT_EXISTS("business.PetNotExists"),
    BUSINESS_INDICATOR_FULL("business.IndicatorFull");

    @Getter
    private final String code;
}
