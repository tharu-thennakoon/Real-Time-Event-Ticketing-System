package com.oopcw.backend.service;

import com.oopcw.backend.entity.User;
import com.oopcw.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        if (!"Producer".equalsIgnoreCase(user.getRole()) && !"Consumer".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("Role must be either 'Producer' or 'Consumer'");
        }
        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
