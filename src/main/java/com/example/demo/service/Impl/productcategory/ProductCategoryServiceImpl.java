package com.example.demo.service.Impl.productcategory;

import com.example.demo.domain.Category;
import com.example.demo.domain.ProductCategory;
import com.example.demo.dto.filter.SearchCriteria;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.product.ProductResponseListDto;
import com.example.demo.service.ProductCategoryCRUDService;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.specifications.SpecificationBuilder;
import com.example.demo.utils.ParseUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ParseUtils parseUtils;
    private final ProductCategoryCRUDService productCategoryCRUDService;
    private final SpecificationBuilder specificationBuilder;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDto<ProductResponseListDto> getListProductByCategory(UUID categoryUuid, int page, int size, String sortBy, String sortType, String search) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        List<SearchCriteria> searchCriteriaList = parseUtils.parseSearch(search);
        if (categoryUuid != null) {
            searchCriteriaList.add(new SearchCriteria("category", ":", new Category(categoryUuid)));
        }
        Page<ProductCategory> productPage = productCategoryCRUDService.findAll(specificationBuilder.createSpecification(searchCriteriaList), pageable);
        PageResponseDto pageResponseDto = PageResponseDto.builder()
                .page(page)
                .size(size)
                .totalPages(productPage.getTotalPages())
                .totalElements(productPage.getTotalElements())
                .elements(productPage.stream().map(productCategory -> modelMapper.map(productCategory, ProductCategory.class)).collect(Collectors.toList())).build();
        return pageResponseDto;
    }

//    }
}
