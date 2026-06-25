package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.*;
import com.ttbtram.pharmacyManagementSystem.repository.HistoryRepository;
import com.ttbtram.pharmacyManagementSystem.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserRepository userRepository;
    //Thêm một History mới
    public History addHistory(@Valid History history) {
        User user = history.getUser();
        if (user == null) {
            throw new RuntimeException("Người dùng không thể trống");
        }
        return historyRepository.save(history);
    }


    // Lấy tất cả các History
    public List<History> getHistories() {
        return historyRepository.findAll();
    }

    // Cập nhật History
    public History updateHistory(Long id, History history) {
        return historyRepository.findById(id)
                .map(history1 -> {
                    history1.setNote(history.getNote());
                    return historyRepository.save(history1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy lịch sử hoạt động"));
    }

    // Tìm kiếm theo id
    public History getHistoryById(Long id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy lịch sử hoạt động: " + id));
    }

    //Tìm kiếm theo id của người dùng
    public List<History> getHistoriesByUserID(Long userID) {
        return historyRepository.findByUser_UserID(userID);
    }
}
