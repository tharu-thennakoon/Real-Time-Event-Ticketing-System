package com.oopcw.backend.service;

import com.oopcw.backend.entity.Configuration;
import com.oopcw.backend.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    private final String configFilePath = "configurations.json";

    // Save configuration and write to a JSON file
    public Configuration saveConfiguration(Configuration configuration) {
        Configuration savedConfig = configurationRepository.save(configuration);
        writeConfigToFile(savedConfig);
        return savedConfig;
    }

    // Update configuration and write to a JSON file
    public Configuration updateConfiguration(Long id, Configuration configuration) {
        if (configurationRepository.existsById(id)) {
            configuration.setId(id);
            Configuration updatedConfig = configurationRepository.save(configuration);
            writeConfigToFile(updatedConfig);
            return updatedConfig;
        }
        throw new RuntimeException("Configuration not found.");
    }

    // Get the last configuration saved
    public Optional<Configuration> getLastConfiguration() {
        return configurationRepository.findAll().stream().findFirst();
    }

    // Write configuration to a JSON file
    private void writeConfigToFile(Configuration config) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(configFilePath), config);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing configuration to file");
        }
    }

    // Load configuration from a JSON file
    public Configuration loadConfigFromFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(configFilePath), Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading configuration from file");
        }
    }
}
