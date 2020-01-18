package ru.tamagotchi.basicmechanics.exception.handler.impl;

import org.springframework.web.context.request.WebRequest;
import ru.tamagotchi.basicmechanics.annotation.HandleCustomException;
import ru.tamagotchi.basicmechanics.dto.ErrorDto;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.exception.NotAcceptedRoomException;
import ru.tamagotchi.basicmechanics.exception.handler.ErrorCode;
import ru.tamagotchi.basicmechanics.exception.handler.api.CustomExceptionHandler;

/**
 * Created by makar
 * 18.12.2018 0:47
 */
@HandleCustomException(NotAcceptedRoomException.class)
public class NotAcceptedRoomExceptionHandler implements CustomExceptionHandler {
    @Override
    public ResponseDto handle(RuntimeException exception, WebRequest request) {
        return new ResponseDto(new ErrorDto(ErrorCode.BUSINESS_INVALID_ROOM));
    }
}
