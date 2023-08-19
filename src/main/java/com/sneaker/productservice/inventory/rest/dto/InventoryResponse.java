package com.sneaker.productservice.inventory.rest.dto;

public class InventoryResponse {
    private Long productId;
    private int size;
    private int quantity;

    public InventoryResponse(Long productId, int size, int quantity) {
        this.productId = productId;
        this.size = size;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
