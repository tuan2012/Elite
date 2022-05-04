package com.example.demo.controller.admin;

import com.example.demo.dto.request.category.CategoryCreateRequestDto;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryAdminController {
    private final CategoryService categoryAdminService;

    @PostMapping("/create")
    public void createCategory(@RequestBody CategoryCreateRequestDto requestDto) {
        categoryAdminService.createCategory(requestDto);
    }

}
