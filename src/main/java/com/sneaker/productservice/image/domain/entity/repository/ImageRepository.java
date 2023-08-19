package com.sneaker.productservice.image.domain.entity.repository;

import com.sneaker.productservice.image.domain.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
