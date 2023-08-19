package com.sneaker.productservice.subscribe.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class SubscribeRequest {
    @Email
    private String email;

    @NotEmpty
    private String name;
    public SubscribeRequest(String email, String name) {
        this.name=name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
