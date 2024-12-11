package org.example;

import java.util.ArrayList;
import java.util.List;

public class TicketingSystem {
    private final TicketPool ticketPool;
    private final List<Thread> threads = new ArrayList<>();

    public TicketingSystem(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void startSystem(int vendors, int customers, int ticketReleaseRate, int customerRetrievalRate, int totalTickets) {
        // Start vendor threads
        for (int i = 0; i < vendors; i++) {
            Thread vendorThread = new Thread(new Vendor(ticketPool, ticketReleaseRate, totalTickets), "Vendor-" + i);
            threads.add(vendorThread);
            vendorThread.start();
        }

        // Start customer threads
        for (int i = 0; i < customers; i++) {
            Thread customerThread = new Thread(new Customer(ticketPool, customerRetrievalRate), "Customer-" + i);
            threads.add(customerThread);
            customerThread.start();
        }

        System.out.println("System started with " + vendors + " vendors and " + customers + " customers.");

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join(); // Wait for each thread to finish
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Simulation completed. All tickets have been issued.");
    }
}
