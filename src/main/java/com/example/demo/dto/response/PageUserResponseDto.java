package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PageUserResponseDto<T> {
    private int page;
    private int size;
    @JsonProperty("total_elements")
    private long totalElements;
    @JsonProperty("total_pages")
    private long totalPages;
    List<T> elements;
}
