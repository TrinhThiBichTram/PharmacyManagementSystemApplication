package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.Customer;
import com.ttbtram.pharmacyManagementSystem.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    // Hiển thị tất cả Customer
    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);
    }


    // Thêm Customer
    @PostMapping("")
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // Cập nhật Customer
    @PutMapping("update/{id}" )
    public Customer updateCustomer(@Valid  @RequestBody Customer customer, @PathVariable Long id) {
        return customerService.updateCustomer(id,customer);
    }

    //Tìm kiếm theo id
    @GetMapping("/customerById/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    //Tìm kiếm theo name
    @GetMapping("/customer/byName/{name}")
    public List<Customer> getCustomersByName(@PathVariable String name) {
        return customerService.getCustomersByName(name);
    }

    //Tìm kiếm theo phone
    @GetMapping("/customer/byPhone/{phone}")
    public List<Customer> getCustomersByPhone(@PathVariable String phone) {
        return customerService.getCustomersByPhone(phone);
    }
}
