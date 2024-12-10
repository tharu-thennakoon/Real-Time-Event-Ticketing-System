package com.oopcw.backend.service;

public class CustomerServiceImpl implements Runnable {
    private final TicketPoolService ticketPool;
    private final int retrievalRate; // In milliseconds

    public CustomerServiceImpl(TicketPoolService ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int ticketId = ticketPool.removeTicket();
                if (ticketId != -1) {
                    System.out.println(Thread.currentThread().getName() + " retrieved Ticket ID: " + ticketId);
                }
                Thread.sleep(retrievalRate); // Simulate ticket retrieval delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Customer thread interrupted: " + e.getMessage());
        }
    }
}
