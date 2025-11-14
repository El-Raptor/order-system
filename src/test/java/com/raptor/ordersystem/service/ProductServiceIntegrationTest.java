package com.raptor.ordersystem.service;


import com.raptor.ordersystem.dto.CreateProductDTO;
import com.raptor.ordersystem.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired
    ProductService productService;

    @Test
    @DisplayName("Should save a product and retrieve it from data base.")
    public void shouldSaveAndRetrieveProduct() {
        var dto = CreateProductDTO.builder()
                .description("Teste")
                .build();

        Product product = productService.save(dto);

        Assertions.assertNotNull(product);
        Assertions.assertEquals(dto.getDescription(), product.getDescription());

        var products = productService.findAll();

        Assertions.assertFalse(products.isEmpty());
        Assertions.assertTrue(products.stream()
                .anyMatch(p -> p.getDescription().equals("Teste")));
    }
}
