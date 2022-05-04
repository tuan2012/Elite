package com.example.demo.service.Impl.category;

import com.example.demo.domain.Category;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryCRUDServiceImpl implements CategoryCRUDService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public Page<Category> findAll(Specification specification, Pageable pageable) {
        return null;
    }

    public List getTopInCategory(UUID id, Pageable pageable) {
        return categoryRepository.getTopFiveInCategory(id, pageable);
    }


    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
