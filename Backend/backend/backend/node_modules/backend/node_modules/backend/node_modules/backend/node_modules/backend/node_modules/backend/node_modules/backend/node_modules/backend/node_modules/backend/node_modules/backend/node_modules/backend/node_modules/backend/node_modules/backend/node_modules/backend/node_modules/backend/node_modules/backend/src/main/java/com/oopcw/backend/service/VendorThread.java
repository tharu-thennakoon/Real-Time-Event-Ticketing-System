package com.oopcw.backend.service;

public class VendorThread implements Runnable {

    private final TicketPoolService ticketPoolService;
    private final int ticketReleaseRate;
    private final int maxTickets;

    public VendorThread(TicketPoolService ticketPoolService, int ticketReleaseRate, int maxTickets) {
        this.ticketPoolService = ticketPoolService;
        this.ticketReleaseRate = ticketReleaseRate;
        this.maxTickets = maxTickets;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < maxTickets; i++) {
                ticketPoolService.addTickets(1);
                Thread.sleep(ticketReleaseRate); // Simulate release delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
