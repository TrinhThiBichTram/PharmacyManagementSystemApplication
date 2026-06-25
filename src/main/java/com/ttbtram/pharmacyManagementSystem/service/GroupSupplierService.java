package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.AlreadyExistsException;
import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.GroupProduct;
import com.ttbtram.pharmacyManagementSystem.model.GroupSupplier;
import com.ttbtram.pharmacyManagementSystem.repository.GroupSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GroupSupplierService {
    private final GroupSupplierRepository groupSupplierRepository;

    // Lấy tất cả các GroupSupplier
    public Page<GroupSupplier> getGroupSuppliers(Pageable pageable) {
        return groupSupplierRepository.findAll(pageable);
    }

    // Thêm một GroupSupplier mới
    public GroupSupplier addGroupSupplier(GroupSupplier groupSupplier) {
        if (groupSupplier.getName() == null || groupSupplier.getName().trim().isEmpty()) {
            throw new NotFoundException("Tên không được để trống");
        }
        if (groupSupplierRepository.existsByName(groupSupplier.getName())) {
            throw new AlreadyExistsException(groupSupplier.getName() + " đã tồn tại");
        }
        return groupSupplierRepository.save(groupSupplier);
    }




    // Cập nhật GroupSupplier
    public GroupSupplier updateGroupSupplier(Long id, GroupSupplier groupSupplier) {
        return groupSupplierRepository.findById(id)
                .map(groupSupplier1 -> {
                    groupSupplier1.setName(groupSupplier.getName());
                    groupSupplier1.setStatus(groupSupplier.getStatus());
                    groupSupplier1.setNote(groupSupplier.getNote());
                    return groupSupplierRepository.save(groupSupplier1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhóm hàng hóa"));
    }


    // Tìm kiếm theo id
    public GroupSupplier getGroupSupplierById(Long id) {
        return groupSupplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm có Id :" +id));
    }
    // Tìm kiếm theo name
    public Page<GroupSupplier> getGroupSuppliersByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (name != null) {
            return groupSupplierRepository.findByNameContaining(name, pageable);
        } else {
            return groupSupplierRepository.findAll(pageable);
        }
    }


}
