package com.oopcw.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oopcw.backend.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long>{
    
}
