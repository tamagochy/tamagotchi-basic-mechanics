package ru.tamagotchi.basicmechanics.exception.handler.api;

import org.springframework.web.context.request.WebRequest;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;

/**
 * Created by makar
 * 02.10.2018 20:28
 */
public interface CustomExceptionHandler {
    ResponseDto handle(RuntimeException exception, WebRequest request);
}
