package com.sneaker.productservice.cart.domain.entity.repository;

import com.sneaker.productservice.cart.domain.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, String> {
}
