package com.sneaker.productservice.order.rest.dto;

public class OrderResponse {

    private Long id;
    private double totalAmount;
    private String name;

    private String email;
    public OrderResponse(Long id, double totalAmount, String name, String email) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.name = name;
        this.email=email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
