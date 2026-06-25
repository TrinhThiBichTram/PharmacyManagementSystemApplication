package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.AlreadyExistsException;
import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.GroupSupplier;
import com.ttbtram.pharmacyManagementSystem.model.Supplier;
import com.ttbtram.pharmacyManagementSystem.repository.GroupSupplierRepository;
import com.ttbtram.pharmacyManagementSystem.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final GroupSupplierRepository groupSupplierRepository;

    // Lấy tất cả các Supplier
    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    // Thêm một Supplier mới
    public Supplier addSupplier(Supplier supplier) {
        if (supplierRepository.existsByName(supplier.getName())) {
            throw new AlreadyExistsException(supplier.getName() +  " đã tồn tại "  );
        }
        if (supplierRepository.existsByEmail(supplier.getEmail())) {
            throw new AlreadyExistsException(supplier.getEmail() +  " đã tồn tại "  );
        }
        if (supplierRepository.existsByPhone(supplier.getPhone())) {
            throw new AlreadyExistsException(supplier.getPhone() +  " đã tồn tại "  );
        }
        return supplierRepository.save(supplier);
    }

    // Cập nhật Supplier
    public Supplier updateSupplier(Long id, Supplier supplier) {
        return supplierRepository.findById(id)
                .map(supplier1 -> {
                    supplier1.setName(supplier.getName());
                    supplier1.setAddress( supplier.getAddress());
                    supplier1.setEmail(supplier.getEmail());
                    supplier1.setNote(supplier.getNote());
                    supplier1.setPhone(supplier.getPhone());
                    supplier1.setStatus(supplier.getStatus());
                    supplier1.setTaxCode(supplier.getTaxCode());
                    GroupSupplier newGroupSupplier = groupSupplierRepository.findById(supplier.getGroupSupplier().getId())
                            .orElseThrow(() -> new NotFoundException("Không tìm thấy nhóm nhà cung cấp với ID: " + supplier.getGroupSupplier().getId()));
                    supplier1.setGroupSupplier(newGroupSupplier);

                    return supplierRepository.save(supplier1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp"));
    }


    // Tìm kiếm theo id
    public Supplier getSupplierById(Long supplierID) {
        return supplierRepository.findById(supplierID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp với ID: " + supplierID));
    }
    // Tìm kiếm theo name
    public List<Supplier> getSuppliersByName(String name) {
        return supplierRepository.findByNameContaining(name);
    }
    // Tìm kiếm theo phone
    public List<Supplier> getSuppliersByPhone(String phone) {
        return supplierRepository.findByPhone(phone);
    }

}
