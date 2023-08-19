package com.sneaker.productservice.product.rest.dto;

import com.sneaker.productservice.product.domain.model.GenderEnum;

import java.util.List;

public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String image;
    private GenderEnum gender;
    private List<Integer> sizes;

    public ProductResponse(Long id, String name, double price, String description, String image,
                           GenderEnum gender, List<Integer> sizes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.gender = gender;
        this.sizes = sizes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public void setSizes(List<Integer> sizes) {
        this.sizes = sizes;
    }
}
