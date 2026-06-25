package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.Inventory;
import com.ttbtram.pharmacyManagementSystem.model.Product;
import com.ttbtram.pharmacyManagementSystem.model.Warehouse;
import com.ttbtram.pharmacyManagementSystem.repository.InventoryRepository;
import com.ttbtram.pharmacyManagementSystem.repository.ProductRepository;
import com.ttbtram.pharmacyManagementSystem.repository.WarehouseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    //Thêm một Inventory mới
    public Inventory addInventory(@Valid Inventory inventory) {
        Product product = inventory.getProduct();
        Warehouse warehouse = inventory.getWarehouse();
        if (product == null || warehouse == null) {
            throw new RuntimeException("Hàng hóa hoặc Kho không thể trống");
        }
        Product product1 = productRepository.findById(inventory.getProduct().getProductID())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với ID: " + inventory.getProduct().getProductID()));
        Warehouse warehouse1 = warehouseRepository.findById(inventory.getWarehouse().getWarehouseID())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy kho với ID: " + inventory.getWarehouse().getWarehouseID()));
        return inventoryRepository.save(inventory);
    }

    // Lấy tất cả các Inventory
    public List<Inventory> getInventories() {
        return inventoryRepository.findAll();
    }
    // Tìm kiếm theo id
    public Inventory getInventoryById(Long inventoryID) {
        return inventoryRepository.findById(inventoryID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy order với ID: " + inventoryID));
    }

    //Tìm kiếm theo id của sản phẩm
    public List<Inventory> getInventoriesByProductID(Long productID) {
        return inventoryRepository.findByProduct_ProductID(productID);
    }

    // Tìm kiếm theo id của kho
    public List<Warehouse> getInventoriesByWarehouseID(Long warehouseID) {
        return inventoryRepository.findByWarehouse_WarehouseID(warehouseID);
    }
}
