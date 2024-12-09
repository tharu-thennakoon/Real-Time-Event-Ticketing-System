package com.oopcw.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopcw.backend.entity.Vendor;
import com.oopcw.backend.repository.VendorRepository;

@Service
public class VendorService {
    
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TicketPoolService ticketPoolService;

    public Vendor createVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }

    public void startVendorThread(Vendor vendor,int maxTickets){
        Thread vendoThread = new Thread(new VendorServiceImpl(ticketPoolService,vendor.getTicketReleaseRate(),maxTickets));
        vendoThread.start();
        System.out.println("Vendor thread started for vendor: " + vendor.getName());
    }
}
