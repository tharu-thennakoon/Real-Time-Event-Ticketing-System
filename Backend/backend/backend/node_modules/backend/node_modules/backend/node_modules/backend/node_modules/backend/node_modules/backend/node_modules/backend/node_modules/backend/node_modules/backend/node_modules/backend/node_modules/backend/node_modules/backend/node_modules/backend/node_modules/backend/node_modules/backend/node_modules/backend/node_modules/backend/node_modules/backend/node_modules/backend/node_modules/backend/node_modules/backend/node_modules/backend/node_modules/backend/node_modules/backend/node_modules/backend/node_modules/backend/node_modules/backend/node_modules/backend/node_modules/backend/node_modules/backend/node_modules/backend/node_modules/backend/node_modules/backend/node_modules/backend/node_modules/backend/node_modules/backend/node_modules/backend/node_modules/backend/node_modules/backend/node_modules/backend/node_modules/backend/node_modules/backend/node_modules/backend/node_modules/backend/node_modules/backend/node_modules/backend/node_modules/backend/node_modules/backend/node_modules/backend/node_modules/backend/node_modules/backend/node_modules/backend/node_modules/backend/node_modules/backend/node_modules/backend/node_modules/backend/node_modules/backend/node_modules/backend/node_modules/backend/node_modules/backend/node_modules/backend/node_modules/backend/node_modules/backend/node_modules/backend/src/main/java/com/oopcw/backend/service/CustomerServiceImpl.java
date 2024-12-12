package com.oopcw.backend.service;

public class CustomerServiceImpl implements Runnable {

    private final TicketPoolService ticketPool;
    private final int retrievalRate;

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
                    System.out.println("Customer retrieved ticket ID: " + ticketId);
                }
                Thread.sleep(retrievalRate); // Simulate delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Customer thread interrupted.");
        }
    }
}
