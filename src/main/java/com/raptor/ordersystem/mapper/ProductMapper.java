package com.raptor.ordersystem.mapper;

import com.raptor.ordersystem.dto.CreateProductDTO;
import com.raptor.ordersystem.dto.ProductDTO;
import com.raptor.ordersystem.entity.Product;

public class ProductMapper {
    public static ProductDTO toDto(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .description(product.getDescription())
                .build();
    }

    public static Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .productId(productDTO.getProductId())
                .description(productDTO.getDescription())
                .build();
    }

    public static Product toEntity(CreateProductDTO productDTO) {
        return Product.builder()
                .description(productDTO.getDescription())
                .build();
    }
}


