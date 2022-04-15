package com.example.demo.exceptions;

import com.example.demo.dto.response.ExceptionResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponseDto> Exception(Exception ex, WebRequest request) {
        ExceptionResponseDto exceptionResponse =
                new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponseDto> handleBadRequestException(BadRequestException ex, WebRequest request) {

        ExceptionResponseDto exceptionResponse =
                new ExceptionResponseDto(ex.getSTATUS().value(), new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, ex.getSTATUS());
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponseDto> handleBadRequestException(NotFoundException ex, WebRequest request) {

        ExceptionResponseDto exceptionResponse =
                new ExceptionResponseDto(ex.getSTATUS().value(), new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, ex.getSTATUS());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponseDto> handleDMSRESTException(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ExceptionResponseDto exceptionResponse =
                new ExceptionResponseDto(HttpStatus.BAD_REQUEST.value(), new Date(), String.join(",", errorList), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
