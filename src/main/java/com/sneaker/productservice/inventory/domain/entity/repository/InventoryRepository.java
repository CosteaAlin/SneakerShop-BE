package com.sneaker.productservice.inventory.domain.entity.repository;

import com.sneaker.productservice.inventory.domain.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    Optional<InventoryEntity> findByProductIdAndSizeId(Long productId, Long sizeId);
}
