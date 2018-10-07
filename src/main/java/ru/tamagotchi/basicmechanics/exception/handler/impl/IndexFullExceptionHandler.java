package ru.tamagotchi.basicmechanics.exception.handler.impl;

import org.springframework.web.context.request.WebRequest;
import ru.tamagotchi.basicmechanics.annotation.HandleCustomException;
import ru.tamagotchi.basicmechanics.dto.ErrorDto;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.exception.handler.IndexFullException;
import ru.tamagotchi.basicmechanics.exception.handler.api.CustomExceptionHandler;

/**
 * Created by makar
 * 07.10.2018 12:09
 */
@HandleCustomException(IndexFullException.class)
public class IndexFullExceptionHandler implements CustomExceptionHandler {
    @Override
    public ResponseDto handle(RuntimeException exception, WebRequest request) {
        IndexFullException indexException = (IndexFullException) exception;
        return ResponseDto.withSingleError(new ErrorDto(indexException.getIndexName(), "index.full"));
    }
}
