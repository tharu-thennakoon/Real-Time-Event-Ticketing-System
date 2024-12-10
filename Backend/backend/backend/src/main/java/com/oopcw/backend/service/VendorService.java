package com.oopcw.backend.service;

import com.oopcw.backend.entity.Vendor;
import com.oopcw.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TicketPoolService ticketPool;

    // Save a Vendor and return the entity (no DTO here)
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    // Find a Vendor by ID (no DTO here)
    public Optional<Vendor> findVendorById(Long id) {
        return vendorRepository.findById(id);
    }

    // Start Vendor Thread
    public void startVendorThread(Vendor vendor, int maxTickets) {
        Thread vendorThread = new Thread(new VendorServiceImpl(ticketPool, vendor.getTicketReleaseRate(), maxTickets));
        vendorThread.start();
        System.out.println("Vendor thread started for vendor: " + vendor.getName());
    }
}
