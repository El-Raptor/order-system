package com.raptor.ordersystem.service;

import com.raptor.ordersystem.dto.CreateProductDTO;
import com.raptor.ordersystem.entity.Product;
import com.raptor.ordersystem.mapper.ProductMapper;
import com.raptor.ordersystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepo;

    ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product save(CreateProductDTO dto) {
        return productRepo.save(ProductMapper.toEntity(dto));
    }

    public Product update(Product product) {
        return productRepo.save(product);
    }

    public void deleteById(Integer id) {
        productRepo.deleteById(id);
    }
}
