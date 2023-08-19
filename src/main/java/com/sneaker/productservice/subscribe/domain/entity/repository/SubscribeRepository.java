package com.sneaker.productservice.subscribe.domain.entity.repository;

import com.sneaker.productservice.subscribe.domain.entity.SubscribeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity, Long> {
}
