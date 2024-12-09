package com.oopcw.backend.controller;

import com.oopcw.backend.entity.User;
import com.oopcw.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // **GET**: Retrieve all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // **GET**: Retrieve a user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // **POST**: Create a new user
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    // **PUT**: Update an existing user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            return userService.saveUser(user);
        }
        throw new RuntimeException("User not found!");
    }

    // **DELETE**: Delete a user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? "User deleted successfully!" : "User not found!";
    }
}
