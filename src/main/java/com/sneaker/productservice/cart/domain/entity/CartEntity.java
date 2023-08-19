package com.sneaker.productservice.cart.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RedisHash("cart")
public class CartEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Map<String, Integer> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Integer> getProducts() {
        if (products == null) {
            products = new HashMap<>();
        }
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }
}
