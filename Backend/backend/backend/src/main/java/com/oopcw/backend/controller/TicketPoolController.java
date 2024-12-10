package com.oopcw.backend.controller;

import com.oopcw.backend.entity.Configuration;
import com.oopcw.backend.service.ConfigurationService;
import com.oopcw.backend.service.TicketPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-pool")
@CrossOrigin
public class TicketPoolController {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketPoolService ticketPoolService;

    // Create new configuration (POST)
    @PostMapping("/configuration")
    public Configuration saveConfiguration(@RequestBody Configuration configuration) {
        return configurationService.saveConfiguration(configuration);
    }

    // Get the last saved configuration (GET)
    @GetMapping("/configuration")
    public Configuration getConfiguration() {
        return configurationService.getLastConfiguration()
                .orElseThrow(() -> new RuntimeException("Configuration not found."));
    }

    // Get a configuration by ID (GET)
    @GetMapping("/configuration/{id}")
    public Configuration getConfigurationById(@PathVariable Long id) {
        return configurationService.getConfiguration(id)
                .orElseThrow(() -> new RuntimeException("Configuration with ID " + id + " not found."));
    }

    // Update existing configuration by ID (PUT)
    @PutMapping("/configuration/{id}")
    public Configuration updateConfiguration(@PathVariable Long id, @RequestBody Configuration configuration) {
        return configurationService.updateConfiguration(id, configuration);
    }

    // Delete configuration by ID (DELETE)
    @DeleteMapping("/configuration/{id}")
    public String deleteConfiguration(@PathVariable Long id) {
        return configurationService.deleteConfiguration(id) ?
                "Configuration deleted successfully" :
                "Configuration not found.";
    }

    // Start simulation
    @GetMapping("/start-simulation")
    public String startSimulation() {
        return ticketPoolService.startSimulation();
    }

    // Stop simulation
    @GetMapping("/stop-simulation")
    public String stopSimulation() {
        return ticketPoolService.stopSimulation();
    }

    // Reset simulation
    @GetMapping("/reset-simulation")
    public String resetSimulation() {
        return ticketPoolService.resetSimulation();
    }

    // Get analytics
    @GetMapping("/analytics")
    public String getAnalytics() {
        return ticketPoolService.getAnalytics();
    }

    // Fetch logs
    @GetMapping("/logs")
    public String getLogs() {
        return ticketPoolService.getLogs();
    }
}
