package com.example.demo.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExpiredException extends RuntimeException {
    private HttpStatus STATUS;

    public ExpiredException(String message) {
        super(message);
        STATUS = HttpStatus.EXPECTATION_FAILED;
    }
}