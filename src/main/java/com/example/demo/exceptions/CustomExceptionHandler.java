package com.example.demo.exceptions;

import com.example.demo.dto.response.ExceptionResponseDto;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ExceptionResponseDto> Exception(Exception ex, WebRequest request) {
//        ExceptionResponseDto exceptionResponse =
//                new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(), request.getDescription(false));
//
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponseDto> handleBadRequestException(BadRequestException ex, WebRequest request) {

        ExceptionResponseDto exceptionResponse =
                new ExceptionResponseDto(ex.getSTATUS().value(), new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, ex.getSTATUS());
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponseDto> handleNotFoundException(NotFoundException ex, WebRequest request) {

        ExceptionResponseDto exceptionResponse =
                new ExceptionResponseDto(ex.getSTATUS().value(), new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, ex.getSTATUS());
    }

    @ExceptionHandler(UserBlockedException.class)
    public final ResponseEntity<ExceptionResponseDto> handleUserBlockedException(UserBlockedException ex, WebRequest request) {

        ExceptionResponseDto exceptionResponse =
                new ExceptionResponseDto(ex.getSTATUS().value(), new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, ex.getSTATUS());
    }

    @ExceptionHandler({MalformedJwtException.class})
    public final ResponseEntity<ExceptionResponseDto> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {

        ExceptionResponseDto exceptionResponse =
                new ExceptionResponseDto(400, new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    ////    ExpiredException.class, UnsupportedJwtException.class, IllegalArgumentException.class,,UnsupportedJwtException.class
//
//
//    @ExceptionHandler({ ExpiredException.class})
//    public final ResponseEntity<ExceptionResponseDto> handleExpiredException(ExpiredException exception, WebRequest request) {
//
//        ExceptionResponseDto exceptionResponse =
//                new ExceptionResponseDto(, new Date(), , request.getDescription(false));
//
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler({ MalformedJwtException.class})
//    public final ResponseEntity<ExceptionResponseDto> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
//
//        ExceptionResponseDto exceptionResponse =
//                new ExceptionResponseDto(400, new Date(), ex.getMessage(), request.getDescription(false));
//
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler({ MalformedJwtException.class})
//    public final ResponseEntity<ExceptionResponseDto> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
//
//        ExceptionResponseDto exceptionResponse =
//                new ExceptionResponseDto(400, new Date(), ex.getMessage(), request.getDescription(false));
//
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler({ MalformedJwtException.class})
//    public final ResponseEntity<ExceptionResponseDto> handleMalformedJwtException(MalformedJwtException ex, WebRequest request) {
//
//        ExceptionResponseDto exceptionResponse =
//                new ExceptionResponseDto(400, new Date(), ex.getMessage(), request.getDescription(false));
//
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
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
