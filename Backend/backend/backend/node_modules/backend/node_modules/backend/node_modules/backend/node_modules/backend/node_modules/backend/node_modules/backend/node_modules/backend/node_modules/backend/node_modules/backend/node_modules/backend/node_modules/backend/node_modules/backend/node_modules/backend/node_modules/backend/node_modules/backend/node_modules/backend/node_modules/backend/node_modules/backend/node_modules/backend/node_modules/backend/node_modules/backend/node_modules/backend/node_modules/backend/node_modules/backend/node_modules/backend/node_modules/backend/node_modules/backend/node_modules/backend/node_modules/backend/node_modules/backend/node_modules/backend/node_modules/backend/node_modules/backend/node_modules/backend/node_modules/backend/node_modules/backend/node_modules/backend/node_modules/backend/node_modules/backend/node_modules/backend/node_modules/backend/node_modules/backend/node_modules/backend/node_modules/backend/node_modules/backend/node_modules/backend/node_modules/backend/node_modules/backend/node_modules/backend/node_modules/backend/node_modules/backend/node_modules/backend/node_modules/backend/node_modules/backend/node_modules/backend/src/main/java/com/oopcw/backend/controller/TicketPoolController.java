package com.oopcw.backend.controller;

import com.oopcw.backend.entity.Configuration;
import com.oopcw.backend.service.ConfigurationService;
import com.oopcw.backend.service.TicketPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/ticket-pool")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketPoolController {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketPoolService ticketPoolService;

    @GetMapping("/start-simulation")
    public ResponseEntity<?> startSimulation() {
        Configuration config = configurationService.getLastConfiguration()
                .orElseThrow(() -> new RuntimeException("No configuration found"));
        
        // Initialize simulation with the configuration
        ticketPoolService.initializeSimulation(config);
        
        String result = ticketPoolService.startSimulation();
        
        // Prepare response with configuration and start status
        Map<String, Object> response = new HashMap<>();
        response.put("status", result);
        response.put("configuration", config);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stop-simulation")
    public String stopSimulation() {
        return ticketPoolService.stopSimulation();
    }

    @GetMapping("/analytics")
    public String getAnalytics() {
        return ticketPoolService.getAnalytics();
    }

    @GetMapping("/logs")
    public List<String> getLogs() {
        return ticketPoolService.getLogs();
    }
}
