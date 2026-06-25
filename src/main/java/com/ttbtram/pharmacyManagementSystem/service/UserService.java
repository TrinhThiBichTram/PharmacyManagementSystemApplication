package com.ttbtram.pharmacyManagementSystem.service;

import com.ttbtram.pharmacyManagementSystem.exception.AlreadyExistsException;
import com.ttbtram.pharmacyManagementSystem.exception.NotFoundException;
import com.ttbtram.pharmacyManagementSystem.model.OrderCustomer;
import com.ttbtram.pharmacyManagementSystem.model.User;
import com.ttbtram.pharmacyManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // Lấy tất cả các User
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Thêm một User mới
    public User addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()) ) {
            throw new AlreadyExistsException(user.getUsername() +  " đã tồn tại");
        }
        return userRepository.save(user);

    }

    // Xóa User
    public void deleteUser(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với ID: " + userID));
        userRepository.deleteById(userID);
    }

    // Cập nhật User
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(user1 -> {
                    user1.setFullName(user.getFullName());
                    user1.setPhone(user.getPhone());
                    user1.setEmail(user.getEmail());
                    user1.setUsername(user.getUsername());
                    user1.setPassword(user.getPassword());
                    user1.setAccount(user.getAccount());
                    return userRepository.save(user1);
                })
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
    }


    // Tìm kiếm theo id
    public User getUserById(Long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với ID: " + userID));
    }
    // Tìm kiếm theo name
    public List<User> getUsersByName(String name) {
        return userRepository.findByFullNameContaining(name);
    }

}
