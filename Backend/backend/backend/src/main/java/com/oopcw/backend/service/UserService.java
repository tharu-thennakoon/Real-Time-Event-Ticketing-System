package com.oopcw.backend.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopcw.backend.dto.UserDTO;
import com.oopcw.backend.entity.User;
import com.oopcw.backend.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Save a user
    public UserDTO saveUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);  // Map UserDTO to User entity
        userRepo.save(user);  // Save the User entity
        return userDTO;
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();  // Fetch all User entities
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
    }

    // Update a user
    public UserDTO updateUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);  // Map UserDTO to User entity
        userRepo.save(user);  // Save updated User entity
        return userDTO;
    }

    public boolean deleteUser(UserDTO userDTO){
        userRepo.delete(modelMapper.map(userDTO,User.class));
        return true;
    }
}
