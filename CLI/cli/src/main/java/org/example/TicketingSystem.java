package org.example;

import java.util.List;
import java.util.ArrayList;

public class TicketingSystem {
    private final TicketPool ticketPool;
    private final List<Thread> threads = new ArrayList<>();

    public TicketingSystem(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void startSystem(int vendors, int customers, int ticketReleaseRate, int customerRetrievalRate){
        for (int i = 0; i < vendors; i++) {
            Thread vendorThread = new Thread(new Vendor(ticketPool, ticketReleaseRate));
            threads.add(vendorThread);
            vendorThread.start();
        }

        for (int i = 0; i < customers; i++) {
            Thread customerThread = new Thread(new Customer(ticketPool, customerRetrievalRate));
            threads.add(customerThread);
            customerThread.start();
        }
        System.out.println("System started with " + vendors + " vendors and " + customers + " customers");

    }

    public void stopSystem(){
        threads.forEach(Thread::interrupt);
        System.out.println("System stopped...");
    }
}
