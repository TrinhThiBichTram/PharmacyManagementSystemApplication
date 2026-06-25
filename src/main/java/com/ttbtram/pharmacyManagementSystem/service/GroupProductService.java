package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.AlreadyExistsException;
import com.ttbtram.pharmacyManagementSystem.model.GroupProduct;
import com.ttbtram.pharmacyManagementSystem.repository.GroupProductRepository;
import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupProductService {

    private final GroupProductRepository groupProductRepository;

    // Lấy tất cả các GroupProduct
    public Page<GroupProduct> getGroupProducts(Pageable pageable) {
        return groupProductRepository.findAll(pageable);
    }
    // Thêm GroupProduct
    public GroupProduct addGroupProduct(GroupProduct groupProduct) {
        if (groupProduct.getName() == null || groupProduct.getName().trim().isEmpty()) {
            throw new NotFoundException("Tên không được để trống");
        }
        if (groupProductRepository.existsByName(groupProduct.getName())) {
            throw new AlreadyExistsException(groupProduct.getName() + " đã tồn tại");
        }
        return groupProductRepository.save(groupProduct);
    }


    // Cập nhật GroupProduct
    public GroupProduct updateGroupProduct(Long id, GroupProduct groupProduct) {
        return groupProductRepository.findById(id)
                .map(groupProduct1 -> {
                    groupProduct1.setName(groupProduct.getName());
                    groupProduct1.setStatus(groupProduct.getStatus());
                    groupProduct1.setNote(groupProduct.getNote());
                    return groupProductRepository.save(groupProduct1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhóm hàng hóa với Id: "+id));
    }


    // Tìm kiếm theo id
    public GroupProduct getGroupProductById(Long id) {
        return groupProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm có Id :" +id));
    }
    //Tìm kiếm theo name
    public Page<GroupProduct> getGroupProductsByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (name != null) {
            return groupProductRepository.findByNameContaining(name, pageable);
        } else {
            return groupProductRepository.findAll(pageable);
        }
    }
}

