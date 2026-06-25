package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.GroupProduct;
import com.ttbtram.pharmacyManagementSystem.model.MedicalInstrument;
import com.ttbtram.pharmacyManagementSystem.model.Medicine;
import com.ttbtram.pharmacyManagementSystem.model.Product;
import com.ttbtram.pharmacyManagementSystem.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    // Hiển thị tất cả Product
    @GetMapping("")
    public Page<Product> getProducts(Pageable pageable) {
        Pageable updatedPageable = PageRequest.of(pageable.getPageNumber(), 10);
        return productService.getProducts(pageable);
    }


    // Thêm Medicine
    @PostMapping("/medicine")
    public Medicine addMedicine(@Valid @RequestBody Medicine medicine) {
        return productService.addMedicine(medicine);
    }

    // Cập nhật Medicine
    @PutMapping("update/medicine/{id}" )
    public Medicine updateMedicine(@Valid @RequestBody Medicine medicine,@PathVariable Long id) {
        return productService.updateMedicine(id,medicine);
    }

    // Thêm MedicalInstrument
    @PostMapping("/medicalInstrument")
    public MedicalInstrument addMedicalInstrument(@Valid @RequestBody MedicalInstrument medicalInstrument) {
        return productService.addMedicalInstrument(medicalInstrument);
    }

    // Cập nhật MedicalInstrument
    @PutMapping("update/medicalInstrument/{id}" )
    public MedicalInstrument updateMedicalInstrument(@Valid @RequestBody MedicalInstrument medicalInstrument,@PathVariable Long id) {
        return productService.updateMedicalInstrument(id, medicalInstrument);
    }

    //Tìm kiếm theo id
    @GetMapping("/productById/{id}")
    public Product getProductById(@PathVariable Long productID){
        return productService.getProductById(productID);
    }

    //Tìm kiếm theo name
    @GetMapping("/product/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

}
