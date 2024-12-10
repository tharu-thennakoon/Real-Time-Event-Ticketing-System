package com.oopcw.backend.service;

import com.oopcw.backend.entity.Configuration;
import com.oopcw.backend.repository.ConfigurationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    private final String configFilePath = "configurations.json";

    // Save configuration and write to JSON file
    public Configuration saveConfiguration(Configuration configuration) {
        Configuration savedConfig = configurationRepository.save(configuration);
        writeConfigToFile(savedConfig);
        return savedConfig;
    }

    // Get the last saved configuration
    public Optional<Configuration> getLastConfiguration() {
        return configurationRepository.findAll().stream().findFirst();
    }

    // Get configuration by ID
    public Optional<Configuration> getConfiguration(Long id) {
        return configurationRepository.findById(id);
    }

    // Update an existing configuration by ID
    public Configuration updateConfiguration(Long id, Configuration configuration) {
        if (configurationRepository.existsById(id)) {
            configuration.setId(id);  // Ensure the ID is set to the existing configuration ID
            Configuration updatedConfig = configurationRepository.save(configuration);
            writeConfigToFile(updatedConfig);  // Optionally save the updated config to the JSON file
            return updatedConfig;
        }
        throw new RuntimeException("Configuration with id " + id + " not found.");
    }

    // Delete configuration by ID
    public boolean deleteConfiguration(Long id) {
        if (configurationRepository.existsById(id)) {
            configurationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Write configuration to a JSON file
    private void writeConfigToFile(Configuration config) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(configFilePath), config);
        } catch (IOException e) {
            throw new RuntimeException("Error writing configuration to file.");
        }
    }
}
