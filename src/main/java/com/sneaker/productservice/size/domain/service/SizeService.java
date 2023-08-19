package com.sneaker.productservice.size.domain.service;

import com.sneaker.productservice.size.domain.entity.SizeEntity;
import com.sneaker.productservice.size.domain.entity.repository.SizeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SizeService {

    private final SizeRepository repository;

    public SizeService(SizeRepository repository) {
        this.repository = repository;
    }

    public SizeEntity getSizeByValue(int size) {
        return repository.findBySizeValue(size).orElseThrow(
                () -> new EntityNotFoundException("Not a valid size")
        );
    }
}
