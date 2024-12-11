package com.oopcw.backend.repository;

import com.oopcw.backend.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}
