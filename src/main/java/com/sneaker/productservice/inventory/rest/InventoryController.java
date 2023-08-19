package com.sneaker.productservice.inventory.rest;

import com.sneaker.productservice.inventory.domain.serivce.InventoryService;
import com.sneaker.productservice.inventory.rest.dto.InventoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<InventoryResponse> getInventoryByProductSize(@RequestParam(name = "productId") Long productId,
                                                                       @RequestParam(name = "sizeId") Long sizeId) {
        return ResponseEntity.ok(service.getInventoryByProductAndSize(productId, sizeId));
    }
}
