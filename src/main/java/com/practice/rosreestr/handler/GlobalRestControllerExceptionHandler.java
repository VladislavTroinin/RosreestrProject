package com.practice.rosreestr.handler;

import com.practice.rosreestr.dto.ErrorDto;
import com.practice.rosreestr.exception.ResourceNotFoundException;
import com.practice.rosreestr.exception.WebServiceNotAccessibleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class GlobalRestControllerExceptionHandler {

    @ExceptionHandler(WebServiceNotAccessibleException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleWebServiceNotAccessibleException(Exception e) {
        Date timestamp = new Date(System.currentTimeMillis());
        return new ResponseEntity<>(
                new ErrorDto(timestamp, "Rosreestr web-service`s error.", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public @ResponseBody ResponseEntity<ErrorDto> handleResourceNotFoundException(Exception e) {
        Date timestamp = new Date(System.currentTimeMillis());
        return new ResponseEntity<>(
                new ErrorDto(timestamp, "Client`s error.", e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

}
