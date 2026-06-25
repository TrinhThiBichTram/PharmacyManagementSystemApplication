package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.Inventory;
import com.ttbtram.pharmacyManagementSystem.model.Warehouse;
import com.ttbtram.pharmacyManagementSystem.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/inventorys")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    // Thêm Inventory
    @PostMapping("")
    public Inventory addInventory(@Valid @RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    // Hiển thị tất cả Inventory
    @GetMapping("")
    public ResponseEntity<List<Inventory>> getAllInventorys() {
        List<Inventory> inventories = inventoryService.getInventories();
        return ResponseEntity.ok(inventories);
    }

    //Tìm kiếm theo id
    @GetMapping("/inventoryById/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    //Tìm kiếm theo id của hàng hóa
    @GetMapping("/inventory/byProductId/{productID}")
    public List<Inventory> getInventoriesByProductID(@PathVariable Long productID) {
        return inventoryService.getInventoriesByProductID(productID);
    }

    //Tìm kiếm theo id của kho
    @GetMapping("/inventory/byWarehouseId/{warehouseID}")
    public List<Warehouse> getInventoriesByWarehouseID(@PathVariable Long warehouseID) {
        return inventoryService.getInventoriesByWarehouseID(warehouseID);
    }
}

