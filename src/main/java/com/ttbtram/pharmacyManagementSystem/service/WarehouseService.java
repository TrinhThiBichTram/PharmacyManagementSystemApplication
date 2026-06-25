package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.AlreadyExistsException;
import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.Warehouse;
import com.ttbtram.pharmacyManagementSystem.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    // Lấy tất cả các Warehouse
    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    // Thêm một Warehouse mới
    public Warehouse addWarehouse(Warehouse warehouse) {
        if (warehouseRepository.existsByName(warehouse.getName())) {
            throw new AlreadyExistsException(warehouse.getName() +  " đã tồn tại");
        }
        return warehouseRepository.save(warehouse);
    }

    // Cập nhật Warehouse
    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        return warehouseRepository.findById(id)
                .map(warehouse1 -> {
                    warehouse1.setName(warehouse.getName());
                    warehouse1.setStatus(warehouse.getStatus());
                    return warehouseRepository.save(warehouse1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy kho"));
    }


    // Tìm kiếm theo id
    public Warehouse getWarehouseById(Long warehouseID) {
        return warehouseRepository.findById(warehouseID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy kho có Id :" + warehouseID));
    }
    // Tìm kiếm theo name
    public List<Warehouse> getWarehousesByName(String name) {
        return warehouseRepository.findByNameContaining(name);
    }

}
