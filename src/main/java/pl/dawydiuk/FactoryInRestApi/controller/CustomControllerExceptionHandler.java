package pl.dawydiuk.FactoryInRestApi.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dawydiuk.FactoryInRestApi.exception.FactoryInRestApiException;

@RestControllerAdvice
public class CustomControllerExceptionHandler{

    @ExceptionHandler(FactoryInRestApiException.class)
    public String restExceptionHandler(FactoryInRestApiException exception) {
        return exception.getMessage();
    }
}
