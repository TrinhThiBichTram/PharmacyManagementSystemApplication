package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.AlreadyExistsException;
import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.Customer;
import com.ttbtram.pharmacyManagementSystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    // Lấy tất cả các Customer
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    // Thêm một Customer mới
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByFullNameAndPhone(customer.getFullName(),customer.getPhone())) {
            throw new AlreadyExistsException(customer.getFullName() +  " đã tồn tại");
        }
        return customerRepository.save(customer);
    }

    // Cập nhật Customer
    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map(customer1 -> {
                    customer1.setFullName(customer.getFullName());
                    customer1.setPhone(customer.getPhone());
                    customer1.setEmail(customer.getEmail());
                    customer1.setAddress(customer.getAddress());
                    customer1.setNote(customer.getNote());
                    return customerRepository.save(customer1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy khách hàng"));
    }


    // Tìm kiếm theo id
    public Customer getCustomerById(Long customerID) {
        return customerRepository.findById(customerID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy khách hàng với ID: " + customerID));
    }
    // Tìm kiếm theo name
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByFullNameContaining(name);
    }
    // Tìm kiếm theo phone
    public List<Customer> getCustomersByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

}
