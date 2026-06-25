package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.OrderCustomer;
import com.ttbtram.pharmacyManagementSystem.service.OrderCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderCustomerController {
    private final OrderCustomerService orderCustomerService;

    // Thêm Order
    @PostMapping("")
    public OrderCustomer addOrderCustomer(@Valid @RequestBody OrderCustomer orderCustomer) {
        return orderCustomerService.addOrderCustomer(orderCustomer);
    }

    // Xóa Order
    @DeleteMapping("/{id}")
    public void deleteOrderCustomer(@PathVariable Long id) {
        orderCustomerService.deleteOrderCustomer(id);
    }

    // Hiển thị tất cả Order
    @GetMapping("")
    public ResponseEntity<List<OrderCustomer>> getAllOrderCustomers() {
        List<OrderCustomer> orderCustomers = orderCustomerService.getOrderCustomers();
        return ResponseEntity.ok(orderCustomers);
    }

    //Tìm kiếm theo id
    @GetMapping("/orderById/{id}")
    public OrderCustomer getOrderCustomerById(@PathVariable Long id) {
        return orderCustomerService.getOrderCustomerById(id);
    }

    //Tìm kiếm theo id của người dùng
    @GetMapping("/order/byUserId/{userID}")
    public List<OrderCustomer> getOrderCustomersByUserID(@PathVariable Long userID) {
        return orderCustomerService.getOrderCustomersByUserID(userID);
    }

    //Tìm kiếm theo id của khách hàng
    @GetMapping("/order/byCustomerId/{customerId}")
    public List<OrderCustomer> getOrdersByCustomerID(@PathVariable Long customerId) {
        return orderCustomerService.getOrdersByCustomerID(customerId);
    }

}
