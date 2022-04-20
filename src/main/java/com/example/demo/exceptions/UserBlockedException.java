package com.example.demo.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserBlockedException extends RuntimeException {
    private final HttpStatus STATUS;

    public UserBlockedException(String message) {
        super(message);
        STATUS = HttpStatus.valueOf(424);
    }
}
