package com.example.demo.service.Impl.product;

import com.example.demo.domain.Product;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.product.ProductResponseListDto;
import com.example.demo.service.ProductAdminService;
import com.example.demo.service.ProductCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAdminServiceImpl implements ProductAdminService {
    private final ProductCRUDService productCRUDService;

    @Override
    public Boolean createProduct(Product product) {
        return null;
    }

    @Override
    public PageResponseDto<ProductResponseListDto> getAllProduct(int page, int size, String sortType, String sortBy, String search) {
        return productCRUDService.getAllProduct(page, size, sortType, sortBy, search);
    }


}
