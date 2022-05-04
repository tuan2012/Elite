package com.example.demo.service;

import com.example.demo.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryCRUDService extends CRUDService<Category, UUID> {
    List<Category> getCategories();
}
