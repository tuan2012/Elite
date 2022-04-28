package com.example.demo.service.Impl.product;

import com.example.demo.domain.Product;
import com.example.demo.dto.filter.SearchCriteria;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.product.ProductResponseListDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductCRUDService;
import com.example.demo.specifications.SpecificationBuilder;
import com.example.demo.utils.ParseSearchUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCRUDServiceImpl implements ProductCRUDService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final SpecificationBuilder specificationBuilder;
    private final ParseSearchUtils parseSearchUtils;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Page<Product> findAll(Specification specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable);
    }

    @Override
    public PageResponseDto<ProductResponseListDto> getAllProduct(int page, int size, String sortType, String sortBy, String search) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        List<SearchCriteria> searchCriteriaList = parseSearchUtils.parseSearch(search);
        Page<Product> productPage = this.findAll(specificationBuilder.createSpecification(searchCriteriaList), pageable);
        PageResponseDto pageResponseDto = PageResponseDto.builder()
                .page(page)
                .size(size)
                .totalPages(productPage.getTotalPages())
                .totalElements(productPage.getTotalElements())
                .elements(productPage.stream().map(product -> modelMapper.map(product, ProductResponseListDto.class)).collect(Collectors.toList())).build();
        return pageResponseDto;
    }
}
