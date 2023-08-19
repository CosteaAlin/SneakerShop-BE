package com.sneaker.productservice.cart.domain.service;

import com.sneaker.productservice.cart.domain.entity.CartEntity;
import com.sneaker.productservice.cart.domain.entity.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    private final CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public CartEntity addToCart(String sessionId, Long productId, int size, int quantity) {
        CartEntity cart;
        if (sessionId == null) {
            cart = createCart();
        } else {
            cart = repository.findById(sessionId).orElseGet(this::createCart);
        }
        String productInCart = productId + "_" + size;
        cart.getProducts().put(productInCart, quantity);
        return repository.save(cart);
    }

    public List<CartEntity> getAllCarts() {
        List<CartEntity> carts = new ArrayList<>();
        repository.findAll().forEach(carts::add);
        return carts;
    }

    public CartEntity removeItemFromCart(String sessionId, Long productId, int size) {
        CartEntity cart = getCart(sessionId);
        String productInCart = productId + "_" + size;
        cart.getProducts().remove(productInCart);
        return repository.save(cart);
    }

    public CartEntity getCart(String sessionId) {
        if (sessionId == null) {
            return createCart();
        } else {
            return repository.findById(sessionId).orElseGet(this::createCart);
        }
    }

    private CartEntity createCart() {
        CartEntity cart = new CartEntity();
        cart.setId(UUID.randomUUID().toString());
        return repository.save(cart);
    }
}
