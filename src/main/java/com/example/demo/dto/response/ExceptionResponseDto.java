package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class ExceptionResponseDto {
    @JsonProperty("status_code")
    private int statusCode;
    private Date date;
    private String message;
    private String details;
}
