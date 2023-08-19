package com.sneaker.productservice.subscribe.rest;

import com.sneaker.productservice.subscribe.domain.entity.SubscribeEntity;
import com.sneaker.productservice.subscribe.domain.service.SubscribeService;
import com.sneaker.productservice.subscribe.rest.dto.SubscribeRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    private final SubscribeService service;

    public SubscribeController(SubscribeService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<SubscribeEntity> addSubscriber(@Valid @RequestBody SubscribeRequest request) {
        return ResponseEntity.ok(service.addSubscriber(request));
    }
}
