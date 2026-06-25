package com.ttbtram.pharmacyManagementSystem.controller;

import com.ttbtram.pharmacyManagementSystem.model.User;
import com.ttbtram.pharmacyManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

     //Hiển thị tất cả User
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> customers = userService.getUsers();
        return ResponseEntity.ok(customers);
    }



    // Thêm User
    @PostMapping("")
    public User addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    // Xóa User
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Cập nhật User
    @PutMapping("update/{id}" )
    public User updateUser(@Valid  @RequestBody User user, @PathVariable Long id) {
        return userService.updateUser(id,user);
    }

    //Tìm kiếm theo id
    @GetMapping("/userById/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //Tìm kiếm theo name
    @GetMapping("/user/byName/{name}")
    public List<User> getUsersByName(@PathVariable String name) {
        return userService.getUsersByName(name);
    }
}
