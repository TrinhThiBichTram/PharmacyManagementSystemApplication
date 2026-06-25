package com.ttbtram.pharmacyManagementSystem.service;


import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.*;
import com.ttbtram.pharmacyManagementSystem.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCustomerService {
    private final OrderCustomerRepository orderCustomerRepository;
    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;

    // Thêm Order
    public OrderCustomer addOrderCustomer(@Valid OrderCustomer orderCustomer) {
        Customer customer = orderCustomer.getCustomer();
        User user = orderCustomer.getUser();

        if (customer == null || user == null) {
            throw new RuntimeException("Khách hàng hoặc Người dùng không thể trống");
        }

        Customer customer1 = customerRepository.findByFullName(orderCustomer.getCustomer().getFullName())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy khách hàng: " + orderCustomer.getCustomer().getFullName()));
        User user1 = userRepository.findById(orderCustomer.getUser().getUserID())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với ID: " + orderCustomer.getUser().getUserID()));

        orderCustomer.setCustomer(customer1);
        orderCustomer.setUser(user1);

        double sum = 0.0;

        for (OrderDetail orderDetail : orderCustomer.getOrderDetails()) {
            Product product = orderDetail.getProduct();
            Warehouse warehouse = orderDetail.getWarehouse();

            int totalStock = 0;
            List<Inventory> inventories = inventoryRepository.findByProduct_ProductIDAndWarehouse_WarehouseID(product.getProductID(), warehouse.getWarehouseID());

            // Cộng dồn số lượng trong từng inventory
            for (Inventory inventory : inventories) {
                totalStock += inventory.getCurrentQty();
            }

            // Kiểm tra xem kho có đủ sản phẩm hay không
            if (totalStock < orderDetail.getQty()) {
                throw new NotFoundException("Sản phẩm " + product.getName() + " chỉ còn " + totalStock + " trong kho.");
            }

            // Tiến hành xử lý việc giảm số lượng trong kho (theo ExpirationDate, nếu cần)
            double total = 0.0;
            inventories.sort(Comparator.comparing(Inventory::getExpirationDate));
            int qtySell = orderDetail.getQty();

            for (Inventory inventory : inventories) {
                if (qtySell == 0) break;

                if (inventory.getExpirationDate().isAfter(LocalDate.now()) && inventory.getCurrentQty() >= qtySell) {
                    inventory.setCurrentQty(inventory.getCurrentQty() - qtySell);
                    inventoryRepository.save(inventory);
                    qtySell = 0;
                    break;
                } else if (inventory.getCurrentQty() > 0) {
                    qtySell = qtySell - inventory.getCurrentQty();
                    inventory.setCurrentQty(0);
                    inventoryRepository.save(inventory);
                }
            }

            total = (product.getPrice() * orderDetail.getQty()) - (product.getDiscount() * (product.getPrice() * orderDetail.getQty()));
            orderDetail.setTotal(total);
            sum += total;
        }
        orderCustomer.setTotal(sum);
        return orderCustomerRepository.save(orderCustomer);
    }


    // Xóa Order
    public void deleteOrderCustomer(Long orderID) {
        OrderCustomer orderCustomer = orderCustomerRepository.findById(orderID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng với ID: " + orderID));
        orderCustomerRepository.deleteById(orderID);
    }




    // Lấy tất cả các OrderCustomer
    public List<OrderCustomer> getOrderCustomers() {
        return orderCustomerRepository.findAll();
    }
    // Tìm kiếm theo id
    public OrderCustomer getOrderCustomerById(Long orderID) {
        return orderCustomerRepository.findById(orderID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy order với ID: " + orderID));
    }

    //Tìm kiếm theo id của người dùng
    public List<OrderCustomer> getOrderCustomersByUserID(Long userID) {
        return orderCustomerRepository.findByUser_UserID(userID);
    }

    // Tìm kiếm theo id của khách hàng
    public List<OrderCustomer> getOrdersByCustomerID(Long customerID) {
        return orderCustomerRepository.findByCustomer_CustomerID(customerID);
    }
}
