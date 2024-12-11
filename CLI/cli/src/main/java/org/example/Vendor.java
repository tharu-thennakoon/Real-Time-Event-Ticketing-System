package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int maxTickets;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int maxTickets) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.maxTickets = maxTickets;
    }

    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (ticketPool) {
                    // Check if total tickets have been issued, if yes, stop the vendor thread
                    if (ticketPool.getTotalTicketsIssued() >= maxTickets) {
                        System.out.println(LocalDateTime.now().format(formatter) + " - " + Thread.currentThread().getName() + " has stopped. All tickets issued.");
                        return; // Stop thread when max tickets are issued
                    }
                }
                ticketPool.addTickets(1); // Add one ticket to the pool
                System.out.println(LocalDateTime.now().format(formatter) + " - " + Thread.currentThread().getName() + " added a ticket.");
                Thread.sleep(ticketReleaseRate * 1000); // Simulate ticket release delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Vendor thread interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred in Vendor thread: " + e.getMessage());
        }
    }
}
