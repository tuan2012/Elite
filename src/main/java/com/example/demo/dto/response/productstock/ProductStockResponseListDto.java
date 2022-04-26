package com.example.demo.dto.response.productstock;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductStockResponseListDto {
    private String productCode;
    private String productName;
    private int productStock;
    private String warehouseCode;
    private String warehouseName;
}
