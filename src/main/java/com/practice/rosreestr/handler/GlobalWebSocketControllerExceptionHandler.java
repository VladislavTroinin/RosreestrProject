package com.practice.rosreestr.handler;

import com.practice.rosreestr.dto.ErrorDto;
import com.practice.rosreestr.exception.ResourceNotFoundException;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Component
public class GlobalWebSocketControllerExceptionHandler {

    @ExceptionHandler(WebServiceNotAccessibleException.class)
    public ErrorDto handleWebServiceNotAccessibleException(Exception e) {
        Date timestamp = new Date(System.currentTimeMillis());
        return new ErrorDto(timestamp, "Rosreestr web-service`s error.", e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorDto handleResourceNotFoundException(Exception e) {
        Date timestamp = new Date(System.currentTimeMillis());
        return new ErrorDto(timestamp, "Client`s error.", e.getMessage());
    }

}
