package com.sneaker.productservice.order.domain.entity.repository;

import com.sneaker.productservice.order.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
