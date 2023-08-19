package com.sneaker.productservice.inventory.domain.serivce;

import com.sneaker.productservice.inventory.domain.entity.InventoryEntity;
import com.sneaker.productservice.inventory.domain.entity.repository.InventoryRepository;
import com.sneaker.productservice.inventory.rest.dto.InventoryResponse;
import com.sneaker.productservice.size.domain.entity.SizeEntity;
import com.sneaker.productservice.size.domain.entity.repository.SizeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository repository;
    private final SizeRepository sizeRepository;

    public InventoryService(InventoryRepository repository, SizeRepository sizeRepository) {
        this.repository = repository;
        this.sizeRepository = sizeRepository;
    }

    public InventoryResponse getInventoryByProductAndSize(Long productId, Long sizeId) {
        InventoryEntity inventoryEntity = getInventoryEntityByProductAndSize(productId, sizeId);
        return mapEntityToResponse(inventoryEntity);
    }

    public InventoryEntity getInventoryEntityByProductAndSize(Long productId, Long sizeId) {
        return repository.findByProductIdAndSizeId(productId, sizeId)
                .orElse(new InventoryEntity());
    }

    public void save(InventoryEntity inventoryEntity) {
        repository.save(inventoryEntity);
    }

    private InventoryResponse mapEntityToResponse(InventoryEntity inventoryEntity) {
        SizeEntity sizeEntity = sizeRepository.findById(inventoryEntity.getSizeId())
                .orElseThrow(() -> new EntityNotFoundException(""));
        return new InventoryResponse(inventoryEntity.getProductId(), sizeEntity.getSizeValue(),
                inventoryEntity.getQuantity());
    }
}
