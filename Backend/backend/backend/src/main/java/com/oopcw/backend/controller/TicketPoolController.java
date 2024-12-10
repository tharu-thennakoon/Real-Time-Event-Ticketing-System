package com.oopcw.backend.controller;

import com.oopcw.backend.service.CustomerServiceImpl;
import com.oopcw.backend.service.TicketPoolService;
import com.oopcw.backend.service.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-pool")
public class TicketPoolController {

    @Autowired
    private TicketPoolService ticketPoolService;

    // Start vendor threads
    @PostMapping("/start-vendors")
    public String startVendorThreads(@RequestParam int count, @RequestParam int ticketReleaseRate, @RequestParam int maxTickets) {
        for (int i = 0; i < count; i++) {
            Thread vendorThread = new Thread(new VendorServiceImpl(ticketPoolService, ticketReleaseRate, maxTickets), "Vendor-" + i);
            vendorThread.start();
        }
        return count + " vendor threads started.";
    }

    // Start customer threads
    @PostMapping("/start-customers")
    public String startCustomerThreads(@RequestParam int count, @RequestParam int retrievalRate) {
        for (int i = 0; i < count; i++) {
            Thread customerThread = new Thread(new CustomerServiceImpl(ticketPoolService, retrievalRate), "Customer-" + i);
            customerThread.start();
        }
        return count + " customer threads started.";
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
}
