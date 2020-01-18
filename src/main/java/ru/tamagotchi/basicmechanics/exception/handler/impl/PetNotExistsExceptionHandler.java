package ru.tamagotchi.basicmechanics.exception.handler.impl;

import org.springframework.web.context.request.WebRequest;
import ru.tamagotchi.basicmechanics.exception.handler.ErrorCode;
import ru.tamagotchi.basicmechanics.annotation.HandleCustomException;
import ru.tamagotchi.basicmechanics.dto.ErrorDto;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.exception.PetNotExistsException;
import ru.tamagotchi.basicmechanics.exception.handler.api.CustomExceptionHandler;

/**
 * Created by makar
 * 07.10.2018 11:57
 */
@HandleCustomException(PetNotExistsException.class)
public class PetNotExistsExceptionHandler implements CustomExceptionHandler {
    @Override
    public ResponseDto handle(RuntimeException exception, WebRequest request) {
        return new ResponseDto(new ErrorDto(ErrorCode.BUSINESS_PET_NOT_EXISTS));
    }
}
