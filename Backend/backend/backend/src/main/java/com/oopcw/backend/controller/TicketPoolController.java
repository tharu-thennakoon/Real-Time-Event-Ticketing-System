package com.oopcw.backend.controller;

import com.oopcw.backend.entity.Configuration;
import com.oopcw.backend.service.ConfigurationService;
import com.oopcw.backend.service.TicketPoolService;
import com.oopcw.backend.service.VendorThread;
import com.oopcw.backend.service.CustomerThread;
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

    // Start vendor and customer threads based on configurations
    @PostMapping("/start-simulation/{configId}")
    public String startSimulation(@PathVariable Long configId) {
        Configuration config = configurationService.getConfiguration(configId)
                .orElseThrow(() -> new RuntimeException("Configuration not found"));

        // Reinitialize ticket pool service based on the configuration
        ticketPoolService.initialize(config.getMaxTicketCapacity());

        // Start vendor threads
        for (int i = 0; i < config.getNumberOfVendors(); i++) {
            Thread vendorThread = new Thread(new VendorThread(ticketPoolService, config.getTicketReleaseRate(), config.getTotalTickets()), "Vendor-" + i);
            vendorThread.start();
        }

        // Start customer threads
        for (int i = 0; i < config.getNumberOfCustomers(); i++) {
            Thread customerThread = new Thread(new CustomerThread(ticketPoolService, config.getCustomerRetrievalRate()), "Customer-" + i);
            customerThread.start();
        }

        return "Simulation started with " + config.getNumberOfVendors() + " vendors and " + config.getNumberOfCustomers() + " customers.";
    }

    // Get the current configuration by ID
    @GetMapping("/configuration/{id}")
    public Configuration getConfiguration(@PathVariable Long id) {
        return configurationService.getConfiguration(id)
                .orElseThrow(() -> new RuntimeException("Configuration not found"));
    }

    // Save a new configuration
    @PostMapping("/configuration")
    public Configuration saveConfiguration(@RequestBody Configuration configuration) {
        validateConfiguration(configuration);
        return configurationService.saveConfiguration(configuration);
    }

    // Update an existing configuration
    @PutMapping("/configuration/{id}")
    public Configuration updateConfiguration(@PathVariable Long id, @RequestBody Configuration configuration) {
        validateConfiguration(configuration);
        return configurationService.updateConfiguration(id, configuration);
    }

    // Delete a configuration by ID
    @DeleteMapping("/configuration/{id}")
    public String deleteConfiguration(@PathVariable Long id) {
        return configurationService.deleteConfiguration(id) ? "Configuration deleted" : "Configuration not found";
    }

    // Get the current ticket pool size
    @GetMapping("/current-size")
    public int getCurrentPoolSize() {
        return ticketPoolService.getCurrentTicketCount();
    }

    // Get the total tickets issued
    @GetMapping("/total-issued")
    public int getTotalTicketsIssued() {
        return ticketPoolService.getTotalTicketsIssued();
    }

    // Validate the configuration object
    private void validateConfiguration(Configuration configuration) {
        if (configuration.getTotalTickets() <= 0) {
            throw new IllegalArgumentException("Total tickets must be greater than 0.");
        }
        if (configuration.getTicketReleaseRate() <= 0) {
            throw new IllegalArgumentException("Ticket release rate must be greater than 0.");
        }
        if (configuration.getCustomerRetrievalRate() <= 0) {
            throw new IllegalArgumentException("Customer retrieval rate must be greater than 0.");
        }
        if (configuration.getMaxTicketCapacity() <= 0) {
            throw new IllegalArgumentException("Maximum ticket capacity must be greater than 0.");
        }
        if (configuration.getNumberOfVendors() <= 0) {
            throw new IllegalArgumentException("Number of vendors must be greater than 0.");
        }
        if (configuration.getNumberOfCustomers() <= 0) {
            throw new IllegalArgumentException("Number of customers must be greater than 0.");
        }
    }
}
