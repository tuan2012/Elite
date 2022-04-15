package com.example.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {
    private final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }

}
