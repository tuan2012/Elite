package com.example.demo.dto.filter;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchCriteria {
    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, String operation, Object value, String previousType) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.previousType = previousType;
    }

    private String key;
    private String operation;
    private Object value;
    private String previousType;

    public Boolean isOrPredicate() {
        return previousType.equals(";");
    }
}
