package com.sneaker.productservice.product.domain.service;

import com.sneaker.productservice.product.domain.entity.ProductEntity;
import com.sneaker.productservice.product.domain.entity.repository.ProductRepository;
import com.sneaker.productservice.product.domain.model.GenderEnum;
import com.sneaker.productservice.product.rest.dto.ProductResponse;
import com.sneaker.productservice.size.domain.entity.SizeEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        return mapEntityToResponse(getProductEntityById(id));
    }

    public ProductEntity getProductEntityById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Entity not found"));
    }

    public List<ProductEntity> getProductsByIds(List<Long> productIds) {
        return productRepository.findAllByIdIn(productIds);
    }

    public List<ProductResponse> getProductsByGender(GenderEnum gender) {
        return productRepository.findByGender(gender)
                .stream()
                .map(this::mapEntityToResponse)
                .toList();
    }

    private ProductResponse mapEntityToResponse(ProductEntity entity) {
        List<Integer> sizes = entity.getSizes().stream()
                .map(SizeEntity::getSizeValue)
                .toList();
        return new ProductResponse(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription(),
                entity.getImage().getImage(), entity.getGender(), sizes);
    }
}
