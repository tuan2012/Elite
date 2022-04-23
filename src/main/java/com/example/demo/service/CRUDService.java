package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CRUDService<T, U> {
    T save(T t);

    T findById(U id);

    Page<T> findAll(Specification specification, Pageable pageable);
}
