package ru.tamagotchi.basicmechanics.controller.advice;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tamagotchi.basicmechanics.annotation.HandleCustomException;
import ru.tamagotchi.basicmechanics.dto.ErrorDto;
import ru.tamagotchi.basicmechanics.dto.ResponseDto;
import ru.tamagotchi.basicmechanics.exception.handler.api.CustomExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by makar
 * 02.10.2018 19:11
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    private final Map<Class<? extends RuntimeException>, CustomExceptionHandler> handlersMap;

    public ExceptionHandlerControllerAdvice(List<CustomExceptionHandler> handlers) {
        handlersMap = new HashMap<>();
        for (CustomExceptionHandler handler : handlers) {
            HandleCustomException annotation = handler.getClass().getAnnotation(HandleCustomException.class);
            if (annotation == null) {
                log.error(
                        "custom exception handler class {} has no HandleCustomException annotation",
                        handler.getClass()
                );
                throw new IllegalStateException();
            }
            log.debug("exception handler {} mapped to exception {}", handler.getClass(), annotation.value());
            handlersMap.put(annotation.value(), handler);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<Object> handle(RuntimeException exception, WebRequest request) throws Exception {
        CustomExceptionHandler handler = handlersMap.get(exception.getClass());
        if (handler != null) {
            return handleExceptionInternal(
                    exception,
                    handler.handle(exception, request),
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST,
                    request
            );
        }
        return handleException(exception, request);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<ErrorDto> errors = new ArrayList<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> errors.add(new ErrorDto(fieldError.getField(), fieldError.getCode())));
        return handleExceptionInternal(
                exception,
                new ResponseDto<>(errors),
                headers,
                status,
                request
        );
    }

    @Override
    @SuppressWarnings("NullableProblems")
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<ErrorDto> errors = new ArrayList<>();
        ((InvalidFormatException) exception.getCause()).getPath()
                .forEach(reference -> errors.add(new ErrorDto(reference.getFieldName(), "validation.Incorrect")));
        return handleExceptionInternal(
                exception,
                new ResponseDto<>(errors),
                headers,
                status,
                request
        );
    }
}
