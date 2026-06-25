package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.Receipt;
import com.ttbtram.pharmacyManagementSystem.service.ReceiptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/receipts")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;

    // Thêm  Receipt
    @PostMapping("")
    public  Receipt addReceipt(@Valid @RequestBody Receipt receipt) {
        return receiptService.addReceipt(receipt);
    }

    // Hiển thị tất cả Receipt
    @GetMapping("")
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        List<Receipt> receipts = receiptService.getReceipts();
        return ResponseEntity.ok(receipts);
    }

    //Tìm kiếm theo id
    @GetMapping("/receiptById/{id}")
    public Receipt getReceiptById(@PathVariable Long receiptID) {
        return receiptService.getReceiptById(receiptID);
    }

    //Tìm kiếm theo id của người dùng
    @GetMapping("/receipt/byUserId/{userID}")
    public List<Receipt> getReceiptsByUserID(@PathVariable Long userID) {
        return receiptService.getReceiptsByUserID(userID);
    }

    //Tìm kiếm theo id của khách hàng
    @GetMapping("/receipt/bySupplierId/{supplierID}")
    public List<Receipt> getReceiptBySupplierID(@PathVariable Long supplierID) {
        return receiptService.getReceiptBySupplierID(supplierID);
    }

}
