package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.Supplier;
import com.ttbtram.pharmacyManagementSystem.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    // Hiển thị tất cả Supplier
    @GetMapping("")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getSuppliers();
        return ResponseEntity.ok(suppliers);
    }


    // Thêm Supplier
    @PostMapping("")
    public Supplier addSupplier(@Valid @RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    // Cập nhật Supplier
    @PutMapping("update/{id}" )
    public Supplier updateSupplier(@Valid  @RequestBody Supplier supplier, @PathVariable Long id) {
        return supplierService.updateSupplier(id,supplier);
    }

    //Tìm kiếm theo id
    @GetMapping("/supplierById/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    //Tìm kiếm theo name
    @GetMapping("/supplier/byName/{name}")
    public List<Supplier> getSuppliersByName(@PathVariable String name) {
        return supplierService.getSuppliersByName(name);
    }

    //Tìm kiếm theo phone
    @GetMapping("/supplier/byPhone/{phone}")
    public List<Supplier> getSuppliersByPhone(@PathVariable String phone) {
        return supplierService.getSuppliersByPhone(phone);
    }

}

