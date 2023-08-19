package com.sneaker.productservice.size.domain.entity.repository;

import com.sneaker.productservice.size.domain.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<SizeEntity, Long> {

    Optional<SizeEntity> findById(Long id);

    Optional<SizeEntity> findBySizeValue(int sizeValue);
}
