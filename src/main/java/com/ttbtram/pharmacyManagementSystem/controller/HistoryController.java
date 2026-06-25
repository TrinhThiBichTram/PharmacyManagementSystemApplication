package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.History;
import com.ttbtram.pharmacyManagementSystem.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/histories")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    // Thêm History
    @PostMapping("")
    public History addHistory(@Valid @RequestBody History history) {
        return historyService.addHistory(history);
    }

    // Hiển thị tất cả History
    @GetMapping("")
    public ResponseEntity<List<History>> getAllHistories() {
        List<History> histories = historyService.getHistories();
        return ResponseEntity.ok(histories);
    }

    //Tìm kiếm theo id
    @GetMapping("/historyById/{id}")
    public History getHistoryById(@PathVariable Long id) {
        return historyService.getHistoryById(id);
    }

    //Tìm kiếm theo id của người dùng
    @GetMapping("/history/byUserId/{userID}")
    public List<History> getHistoriesByUserID(@PathVariable Long userID) {
        return historyService.getHistoriesByUserID(userID);
    }
}
