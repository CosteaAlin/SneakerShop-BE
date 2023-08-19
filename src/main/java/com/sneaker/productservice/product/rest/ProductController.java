package com.sneaker.productservice.product.rest;

import com.sneaker.productservice.product.domain.model.GenderEnum;
import com.sneaker.productservice.product.domain.service.ProductService;
import com.sneaker.productservice.product.rest.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/gender")
    public ResponseEntity<List<ProductResponse>> getProductsByGender(@RequestParam("gender") GenderEnum gender) {
        return ResponseEntity.ok(productService.getProductsByGender(gender));
    }
}
