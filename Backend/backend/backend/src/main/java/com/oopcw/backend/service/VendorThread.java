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
            while (ticketPoolService.getTotalTicketsIssued() < maxTickets) {
                ticketPoolService.addTickets(1);
                Thread.sleep(ticketReleaseRate);  // Simulate ticket release
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
