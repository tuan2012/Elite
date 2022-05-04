package com.example.demo.service.Impl.category;

import com.example.demo.domain.Category;
import com.example.demo.dto.request.category.CategoryCreateRequestDto;
import com.example.demo.dto.response.category.CategoryCreateResponseDto;
import com.example.demo.service.CategoryCRUDService;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryCRUDService categoryCRUDService;
    private final ModelMapper modelMapper;

    @Override
    public CategoryCreateResponseDto createCategory(CategoryCreateRequestDto dto) {
        Category map = modelMapper.map(dto, Category.class);
        return modelMapper.map(categoryCRUDService.save(map), CategoryCreateResponseDto.class);
    }

    @Override
    public List<CategoryCreateResponseDto> getCategories() {
        return categoryCRUDService.getCategories().stream().map(category -> modelMapper.map(category, CategoryCreateResponseDto.class)).collect(Collectors.toList());
    }
}
