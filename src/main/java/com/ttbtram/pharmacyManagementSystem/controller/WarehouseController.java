package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.Warehouse;
import com.ttbtram.pharmacyManagementSystem.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    // Hiển thị tất cả Warehouse
    @GetMapping("")
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getWarehouses();
        return ResponseEntity.ok(warehouses);
    }


    // Thêm Warehouse
    @PostMapping("")
    public Warehouse addWarehouse(@Valid @RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }

    // Cập nhật Warehouse
    @PutMapping("update/{id}" )
    public Warehouse updateWarehouse(@Valid  @RequestBody Warehouse warehouse, @PathVariable Long id) {
        return warehouseService.updateWarehouse(id,warehouse);
    }

    //Tìm kiếm theo id
    @GetMapping("/warehouseById/{id}")
    public Warehouse getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id);
    }

    //Tìm kiếm theo name
    @GetMapping("/warehouse/byName/{name}")
    public List<Warehouse> getWarehousesByName(@PathVariable String name) {
        return warehouseService.getWarehousesByName(name);
    }
}
