package com.oopcw.backend.service;

import com.oopcw.backend.entity.Configuration;
import com.oopcw.backend.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    // Save configuration
    public Configuration saveConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    // Update configuration
    public Configuration updateConfiguration(Long id, Configuration configuration) {
        if (configurationRepository.existsById(id)) {
            configuration.setId(id);
            return configurationRepository.save(configuration);
        }
        throw new RuntimeException("Configuration not found.");
    }

    // Delete configuration
    public boolean deleteConfiguration(Long id) {
        if (configurationRepository.existsById(id)) {
            configurationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get the configuration by ID
    public Optional<Configuration> getConfiguration(Long id) {
        return configurationRepository.findById(id);
    }
}
