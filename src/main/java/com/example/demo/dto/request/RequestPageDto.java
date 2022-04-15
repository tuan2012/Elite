package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPageDto {
    private int page;
    private int size;
    private String sortType;
    private String sortField;
}
