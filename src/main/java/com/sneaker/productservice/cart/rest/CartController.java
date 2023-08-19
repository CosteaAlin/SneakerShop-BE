package com.sneaker.productservice.cart.rest;

import com.sneaker.productservice.cart.domain.entity.CartEntity;
import com.sneaker.productservice.cart.domain.service.CartService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/update")
    public ResponseEntity<CartEntity> updateCart(@RequestParam Long productId,
                                                 @RequestParam int size,
                                                 @RequestParam int quantity,
                                                 @CookieValue(name = "sessionId", required = false) String sessionId,
                                                 HttpServletResponse response) {
        CartEntity cart = cartService.addToCart(sessionId, productId, size, quantity);
        Cookie cookie = new Cookie("sessionId", cart.getId());
        cookie.setMaxAge(20 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().body(cart);
    }

    @GetMapping
    public ResponseEntity<List<CartEntity>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/session")
    public ResponseEntity<CartEntity> getCartBySession(@CookieValue(name = "sessionId", required = false) String sessionId) {
        return ResponseEntity.ok(cartService.getCart(sessionId));
    }

    @DeleteMapping("/delete-item")
    public ResponseEntity<CartEntity> removeItemFromCart(@CookieValue(name = "sessionId") String sessionId,
                                                         @RequestParam Long productId,
                                                         @RequestParam int size){
        return ResponseEntity.ok(cartService.removeItemFromCart(sessionId, productId, size));
    }
}
