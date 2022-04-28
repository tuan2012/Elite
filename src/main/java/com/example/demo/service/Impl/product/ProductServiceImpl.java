package com.example.demo.service.Impl.product;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductRating;
import com.example.demo.domain.ProductReview;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.product.ProductDetailResponseDto;
import com.example.demo.dto.response.product.ProductResponseListDto;
import com.example.demo.service.ProductCRUDService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductCRUDService productCRUDService;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDto<ProductResponseListDto> getAllProduct(int page, int size, String sortType, String sortBy, String search) {
        StringBuilder searchQuery = new StringBuilder();
        searchQuery.append(search).append("stock:>0");
        return productCRUDService.getAllProduct(page, size, sortType, sortBy, search);
    }

    @Override
    public ProductDetailResponseDto getProductDetail(UUID id) {
        productCRUDService.findById(id);
        return modelMapper.map(productCRUDService.findById(id), ProductDetailResponseDto.class);
    }

    @Override
    public Boolean addComment(UUID id, String comment) {
        Product product = productCRUDService.findById(id);
        Set<ProductReview> productReviews = product.getProductReviews();
        ProductReview productReview = ProductReview.builder().value(comment).build();
        productReviews.add(productReview);
        try {
            productCRUDService.save(product);
        } catch (Exception e) {
            throw new RuntimeException("add comment product fail");
        }
        return true;
    }

    @Override
    public Boolean addRating(UUID id, int rating) {
        Product product = productCRUDService.findById(id);
        Set<ProductRating> productRatings = product.getProductRatings();
        ProductRating productRating = ProductRating.builder().rating(rating).build();
        productRatings.add(productRating);
        try {
            productCRUDService.save(product);
        } catch (Exception e) {
            throw new RuntimeException("add rating product fail");
        }
        return true;
    }
}
