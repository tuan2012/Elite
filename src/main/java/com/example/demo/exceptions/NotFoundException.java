package com.example.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {
    private final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

}
