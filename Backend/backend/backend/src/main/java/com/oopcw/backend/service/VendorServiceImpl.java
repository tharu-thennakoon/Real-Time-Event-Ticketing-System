package com.oopcw.backend.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class VendorServiceImpl implements Runnable {
    private final TicketPoolService ticketPool;
    private final int ticketReleaseRate;
    private final int maxTickets;

    public VendorServiceImpl(TicketPoolService ticketPool, int ticketReleaseRate, int maxTickets) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.maxTickets = maxTickets;
    }

    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            while (true) {
                synchronized (ticketPool) {
                    if (ticketPool.getTotalTicketsIssued() >= maxTickets) {
                        System.out.println(LocalDateTime.now().format(formatter) + " - Vendor has stopped. All tickets issued.");
                        break;
                    }
                }
                ticketPool.addTickets(1);
                System.out.println(LocalDateTime.now().format(formatter) + " - Vendor added a ticket.");
                Thread.sleep(ticketReleaseRate * 1000); // Simulate ticket release delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Vendor thread interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred in Vendor thread: " + e.getMessage());
        }
    }
}
