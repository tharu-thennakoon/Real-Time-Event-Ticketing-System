package com.oopcw.backend.service;

public class VendorServiceImpl implements Runnable {
    private final TicketPoolService ticketPool;
    private final int ticketReleaseRate; // In milliseconds
    private final int maxTickets;

    public VendorServiceImpl(TicketPoolService ticketPool, int ticketReleaseRate, int maxTickets) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.maxTickets = maxTickets;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (ticketPool) {
                    if (ticketPool.getTotalTicketsIssued() >= maxTickets) {
                        System.out.println(Thread.currentThread().getName() + " has stopped. All tickets issued.");
                        break;
                    }
                }
                ticketPool.addTickets(1);
                Thread.sleep(ticketReleaseRate); // Simulate ticket release delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Vendor thread interrupted: " + e.getMessage());
        }
    }
}