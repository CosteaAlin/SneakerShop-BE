package com.sneaker.productservice.order.rest;

import com.sneaker.productservice.order.domain.service.OrderService;
import com.sneaker.productservice.order.rest.dto.OrderRequest;
import com.sneaker.productservice.order.rest.dto.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody @Valid OrderRequest request,
                                                  @CookieValue(name = "sessionId") String sessionId){
        return ResponseEntity.ok(orderService.addOrder(request, sessionId));
    }
}
