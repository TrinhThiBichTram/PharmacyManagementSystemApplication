package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.*;
import com.ttbtram.pharmacyManagementSystem.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;

    public Receipt addReceipt(@Valid Receipt receipt) {
        Supplier supplier = receipt.getSupplier();
        User user = receipt.getUser();

        if (supplier == null || user == null) {
            throw new RuntimeException("Nhà cung cấp hoặc Người dùng không thể trống");
        }

        // Tìm nhà cung cấp và người dùng trong CSDL
        Supplier supplier1 = supplierRepository.findByName(receipt.getSupplier().getName())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhà cung cấp: " + receipt.getSupplier().getName()));
        User user1 = userRepository.findById(receipt.getUser().getUserID())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với ID: " + receipt.getUser().getUserID()));

        receipt.setSupplier(supplier1);
        receipt.setUser(user1);

        double totalPurchaseAmount = 0.0;

        // Duyệt qua từng chi tiết phiếu nhập hàng
        for (ReceiptDetail receiptDetail : receipt.getReceiptDetails()) {
            Product product = receiptDetail.getProduct();
            Warehouse warehouse = receiptDetail.getWarehouse();
            int qtyToAdd = receiptDetail.getQty();
            LocalDate expirationDate = receiptDetail.getExpirationDate();  // Ngày hết hạn từ ReceiptDetail

            // Kiểm tra xem sản phẩm và kho có tồn tại trong CSDL không
            if (product == null || warehouse == null) {
                throw new RuntimeException("Sản phẩm hoặc Kho không hợp lệ");
            }

            // Kiểm tra sự tồn tại của Inventory cho Product và Warehouse này
            List<Inventory> existingInventories = inventoryRepository.findByProduct_ProductIDAndWarehouse_WarehouseID(product.getProductID(), warehouse.getWarehouseID());

            boolean inventoryUpdated = false;

            // Nếu tồn tại Inventory, tìm Inventory có cùng expirationDate (nếu có)
            for (Inventory inventory : existingInventories) {
                if (inventory.getExpirationDate() != null && expirationDate != null &&
                        inventory.getExpirationDate().equals(expirationDate)) {
                    inventory.setCurrentQty(inventory.getCurrentQty() + qtyToAdd);
                    inventoryRepository.save(inventory);
                    inventoryUpdated = true;
                    break;
                }
            }

            // Nếu không tìm thấy Inventory có expirationDate khớp hoặc không có expirationDate
            if (!inventoryUpdated) {
                // Tạo mới Inventory nếu không tồn tại
                Inventory newInventory = new Inventory();
                newInventory.setProduct(product);
                newInventory.setWarehouse(warehouse);
                newInventory.setCurrentQty(qtyToAdd);
                newInventory.setExpirationDate(expirationDate);

                inventoryRepository.save(newInventory); // Lưu Inventory mới vào cơ sở dữ liệu
            }

            // Tính tổng giá trị của phiếu nhập (nếu cần)
            double total = (product.getPrice() * qtyToAdd); // Chưa trừ giảm giá nếu có
            receiptDetail.setTotal(total);
            totalPurchaseAmount += total;
        }

        receipt.setTotal(totalPurchaseAmount);
        return receiptRepository.save(receipt);
    }





    // Lấy tất cả các Receipt
    public List<Receipt> getReceipts() {
        return receiptRepository.findAll();
    }
    // Tìm kiếm theo id
    public Receipt getReceiptById(Long receiptID) {
        return receiptRepository.findById(receiptID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy receipt với ID: " + receiptID));
    }

    //Tìm kiếm theo id của người dùng
    public List<Receipt> getReceiptsByUserID(Long userID) {
        return receiptRepository.findByUser_UserID(userID);
    }

    // Tìm kiếm theo id của nhà cung cấp
    public List<Receipt> getReceiptBySupplierID(Long supplierID) {
        return receiptRepository.findBySupplier_SupplierID(supplierID);
    }
}
