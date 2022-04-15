package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PageUserResponseDto<T> {
    private int page;
    private int size;
    private long totalElements;
    private long totalPages;
    List<T> elements;
}
