package com.example.demo.service.productstock.Impl;

import com.example.demo.domain.ProductStock;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.productstock.ProductStockResponseListDto;
import com.example.demo.service.productstock.ProductStockAdminService;
import com.example.demo.service.productstock.ProductStockCRUDService;
import com.example.demo.specifications.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductStockAdminServiceImpl implements ProductStockAdminService {
    private final ProductStockCRUDService productStockCRUDService;
    private final SpecificationBuilder specificationBuilder;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDto<ProductStockResponseListDto> getProductStocks(int page, int size, String sortType, String sortBy, String search) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));


        Page<ProductStock> productStockPage = productStockCRUDService.findAll(specificationBuilder.createSpecification(search), pageable);
        PageResponseDto pageResponseDto = new PageResponseDto<>();
        pageResponseDto.setPage(page);
        pageResponseDto.setSize(size);
        pageResponseDto.setTotalPages(productStockPage.getTotalPages());
        pageResponseDto.setTotalElements(productStockPage.getNumberOfElements());
        pageResponseDto.setElements(productStockPage.stream().map(productStock -> {
            return ProductStockResponseListDto.builder()
                    .productStock(productStock.getStock())
                    .productCode(productStock.getProduct().getCode())
                    .productName(productStock.getProduct().getName())
                    .warehouseCode(productStock.getWarehouse().getCode())
                    .warehouseName(productStock.getWarehouse().getName()).build();
        }).collect(Collectors.toList()));
        return pageResponseDto;
    }
}
