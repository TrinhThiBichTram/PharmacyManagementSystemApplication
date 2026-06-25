package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.GroupProduct;
import com.ttbtram.pharmacyManagementSystem.model.GroupSupplier;
import com.ttbtram.pharmacyManagementSystem.service.GroupProductService;
import com.ttbtram.pharmacyManagementSystem.service.GroupSupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/groupSuppliers")
@RequiredArgsConstructor
public class GroupSupplierController {
    private final GroupSupplierService groupSupplierService;

    // Hiển thị tất cả GroupSupplier
    @GetMapping("")
    public Page<GroupSupplier> getGroupSuppliers(Pageable pageable) {
        Pageable updatedPageable = PageRequest.of(pageable.getPageNumber(), 10);
        return groupSupplierService.getGroupSuppliers(pageable);
    }


    // Thêm GroupSupplier
    @PostMapping("")
    public GroupSupplier addGroupSupplier(@Valid @RequestBody GroupSupplier groupSupplier) {
        return groupSupplierService.addGroupSupplier(groupSupplier);
    }

    // Cập nhật GroupSupplier
    @PutMapping("update/{id}")
    public GroupSupplier updateGroupSupplier(@Valid @RequestBody GroupSupplier groupSupplier, @PathVariable Long id) {
        return groupSupplierService.updateGroupSupplier(id, groupSupplier);
    }

    //Tìm kiếm theo id
    @GetMapping("/groupSupplierById/{id}")
    public GroupSupplier getGroupSupplierById(@PathVariable Long id) {
        return groupSupplierService.getGroupSupplierById(id);
    }

    //Tìm kiếm theo name
    @GetMapping("/search")
    public Page<GroupSupplier> getGroupSuppliersByNameOrId(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        {
            return groupSupplierService.getGroupSuppliersByName(name, page, size);
        }
    }
}