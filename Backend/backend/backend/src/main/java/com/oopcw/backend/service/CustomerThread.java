package com.oopcw.backend.service;

public class CustomerThread implements Runnable {

    private final TicketPoolService ticketPoolService;
    private final int retrievalRate;

    public CustomerThread(TicketPoolService ticketPoolService, int retrievalRate) {
        this.ticketPoolService = ticketPoolService;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        try {
            while (ticketPoolService.getTotalTicketsIssued() > 0) {
                ticketPoolService.removeTicket();
                Thread.sleep(retrievalRate);  // Simulate ticket retrieval
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
