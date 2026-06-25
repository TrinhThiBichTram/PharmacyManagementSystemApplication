package com.ttbtram.pharmacyManagementSystem.repository;

import com.ttbtram.pharmacyManagementSystem.model.Inventory;
import com.ttbtram.pharmacyManagementSystem.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findById(Long inventoryID);
    List<Inventory> findByProduct_ProductID(Long productID);
    List<Warehouse> findByWarehouse_WarehouseID(Long warehouseID);
    List<Inventory> findByProduct_ProductIDAndWarehouse_WarehouseID(Long productID, Long warehouseID);
    // Truy vấn tổng tồn kho của một sản phẩm
    @Query("SELECT SUM(i.currentQty) FROM Inventory i WHERE i.product.productID = :productId")
    int getTotalStockForProduct(@Param("productId") Long productId);
}
