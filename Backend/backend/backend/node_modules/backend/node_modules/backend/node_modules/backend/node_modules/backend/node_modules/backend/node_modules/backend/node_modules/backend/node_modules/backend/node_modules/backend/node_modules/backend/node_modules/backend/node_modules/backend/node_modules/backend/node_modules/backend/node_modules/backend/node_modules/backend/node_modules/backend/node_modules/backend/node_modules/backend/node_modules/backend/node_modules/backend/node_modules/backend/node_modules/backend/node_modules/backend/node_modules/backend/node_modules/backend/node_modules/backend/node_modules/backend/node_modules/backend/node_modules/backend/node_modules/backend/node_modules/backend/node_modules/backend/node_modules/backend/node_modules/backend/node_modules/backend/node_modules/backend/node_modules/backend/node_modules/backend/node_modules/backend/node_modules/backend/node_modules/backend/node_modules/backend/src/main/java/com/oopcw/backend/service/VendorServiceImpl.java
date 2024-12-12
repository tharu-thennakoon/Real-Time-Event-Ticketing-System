package com.oopcw.backend.service;

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
        try {
            for (int i = 0; i < maxTickets; i++) {
                ticketPool.addTickets(1);
                Thread.sleep(ticketReleaseRate); // Simulate release delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Vendor thread interrupted.");
        }
    }
}
