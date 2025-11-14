package com.raptor.ordersystem.controller;

import com.raptor.ordersystem.dto.CreateProductDTO;
import com.raptor.ordersystem.dto.ProductDTO;
import com.raptor.ordersystem.mapper.ProductMapper;
import com.raptor.ordersystem.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> findAll() {
        var products = productService.findAll().stream()
                .map(ProductMapper::toDto)
                .toList();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        var product = productService.findById(id);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ProductMapper.toDto(product), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CreateProductDTO> save(@RequestBody CreateProductDTO productDTO) {
        var product = productService.save(productDTO);
        URI uri = URI.create("/api/product/" + product.getProductId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody CreateProductDTO productDTO) {
        var product = productService.findById(id);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        product.setDescription(productDTO.getDescription());
        return ResponseEntity.ok(ProductMapper.toDto(productService.update(product)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        var product = productService.findById(id);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
