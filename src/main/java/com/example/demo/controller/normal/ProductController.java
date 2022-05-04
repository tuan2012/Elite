package com.example.demo.controller.normal;

import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/normal/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public void getProductList(@RequestParam(value = "search", required = false) String search,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "4") int size,
                               @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
                               @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType) {
        productService.getAllProduct(page, size, sortType, sortBy, search);
    }

}

