package org.example;
import java.util.List;
import java.lang.module.Configuration;
import java.util.ArrayList;

public class TicketingSystem {
    private final TicketPool ticketPool;
    private final Configuration config;
    private final List<Thread> customerthreads;
    private final List<Thread> vendorThreads;
    private boolean running;

    public TicketingSystem(final Configuration config) {
        this.config = config;
        this.ticketPool = new TicketPool(config.getMaxTicketCapacity());
        this.customerthreads = new ArrayList<>();
        this.vendorThreads = new ArrayList<>();
        this.running = false;

        for (int i=0; i<config.getTotalTickets();i++){
            ticketPool.addTicket(new Ticket(i,1));

        }
    }

    public synchronized start() {
        if (!running) return;
        running = true;

        for (int i = 0; i < 2;i++){
            Vendor vendor = new Vendor(i,ticketPool,config);
            Thread vendorThread = new Thread(vendor);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        for (int i = 0; i < config.getTotalTicket();i++){
            Customer customer = new Customer(i,ticketPool,config);
            Thread customerThread = new Thread(customer);
            customerthreads.add(customerThread);
            customerThread.start();
        }
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;

        for (Thread thread : vendorThreads){
            thread.interrupt();
        }

        for (Thread thread : customerthreads){
            thread.interrupt();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public int getAvailableTickets() {
        return ticketPool.getTicketCount;
    }
}
