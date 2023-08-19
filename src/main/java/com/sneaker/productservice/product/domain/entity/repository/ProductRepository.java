package com.sneaker.productservice.product.domain.entity.repository;

import com.sneaker.productservice.product.domain.entity.ProductEntity;
import com.sneaker.productservice.product.domain.model.GenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByIdIn(List<Long> ids);

    List<ProductEntity> findByGender(GenderEnum gender);
}
