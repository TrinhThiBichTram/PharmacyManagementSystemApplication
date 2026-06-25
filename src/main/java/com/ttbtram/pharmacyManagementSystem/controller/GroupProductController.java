package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.GroupProduct;
import com.ttbtram.pharmacyManagementSystem.service.GroupProductService;
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
@RequestMapping("api/groupProducts")
@RequiredArgsConstructor
public class GroupProductController {
    private final GroupProductService groupProductService;

    // Hiển thị tất cả GroupProduct
    @GetMapping("")
    public Page<GroupProduct> getGroupProducts(Pageable pageable) {
        Pageable updatedPageable = PageRequest.of(pageable.getPageNumber(), 10);
        return groupProductService.getGroupProducts(pageable);
    }


    // Thêm GroupProduct
    @PostMapping("")
    public GroupProduct addGroupProduct(@Valid @RequestBody GroupProduct groupProduct) {
        return groupProductService.addGroupProduct(groupProduct);
    }

    // Cập nhật GroupProduct
    @PutMapping("update/{id}")
    public GroupProduct updateGroupProduct(@Valid @RequestBody GroupProduct groupProduct, @PathVariable Long id) {
        return groupProductService.updateGroupProduct(id, groupProduct);
    }

    //Tìm kiếm theo id
    @GetMapping("/groupProductById/{id}")
    public GroupProduct getGroupProductById(@PathVariable Long id) {
        return groupProductService.getGroupProductById(id);
    }

    //Tìm kiếm theo name
    @GetMapping("/search")
    public Page<GroupProduct> getGroupProductsByNameOrId(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        {
            return groupProductService.getGroupProductsByName(name, page, size);
        }
    }
}