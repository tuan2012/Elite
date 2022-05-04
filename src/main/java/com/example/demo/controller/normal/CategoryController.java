package com.example.demo.controller.normal;

import com.example.demo.dto.response.ResponseBodyDto;
import com.example.demo.dto.response.category.CategoryListResponseDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/normal/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductCategoryService productCategoryService;

    @GetMapping("/all")
    public ResponseEntity<ResponseBodyDto<List<CategoryListResponseDto>>> getCategoryList() {
        ResponseBodyDto responseBodyDto = new ResponseBodyDto();
        responseBodyDto.setData(categoryService.getCategories());
        responseBodyDto.setStatusCode(200);
        return ResponseEntity.ok(responseBodyDto);
    }

    @GetMapping("/{id}")
    public void getListProductByCategory(@RequestParam(value = "search", required = false) String search,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "4") int size,
                                         @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
                                         @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                         @PathVariable(name = "id") UUID categoryUuid) {
        productCategoryService.getListProductByCategory(categoryUuid, page, size, sortBy, sortType, search);
    }

}
