package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class ExceptionResponseDto {
    private int statusCode;
    private Date date;
    private String message;
    private String details;
}
