package ru.tamagotchi.basicmechanics.exception.handler.impl;


import org.springframework.web.context.request.WebRequest;
import ru.tamagotchi.basicmechanics.exception.handler.ErrorCode;
import ru.tamagotchi.basicmechanics.annotation.HandleCustomException;
import ru.tamagotchi.basicmechanics.dto.ErrorDto;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.exception.PetNotFoundException;
import ru.tamagotchi.basicmechanics.exception.handler.api.CustomExceptionHandler;

/**
 * Created by makar
 * 02.10.2018 20:29
 */
@HandleCustomException(PetNotFoundException.class)
public class PetNotFoundExceptionHandler implements CustomExceptionHandler {
    @Override
    public ResponseDto handle(RuntimeException exception, WebRequest request) {
        return new ResponseDto(new ErrorDto(ErrorCode.BUSINESS_PET_NOT_FOUND));
    }
}
