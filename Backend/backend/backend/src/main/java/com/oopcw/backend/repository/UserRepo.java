package com.oopcw.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oopcw.backend.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    
}
