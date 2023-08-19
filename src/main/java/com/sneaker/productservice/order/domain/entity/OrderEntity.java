package com.sneaker.productservice.order.domain.entity;

import com.sneaker.productservice.customer.domain.entity.CustomerEntity;
import com.sneaker.productservice.product.domain.entity.ProductEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"Orders\"")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    private String status;
    @Column(name = "total_amount")
    private double totalAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @ManyToMany
    @JoinColumn(name = "product_id")
    private List<ProductEntity> products;

    public OrderEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
