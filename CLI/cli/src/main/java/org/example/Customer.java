package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int ticketId = ticketPool.removeTicket();
                if (ticketId != -1) {
                    System.out.println(LocalDateTime.now().format(formatter) + " - " + Thread.currentThread().getName() + " retrieved Ticket ID " + ticketId);
                }
                Thread.sleep(customerRetrievalRate * 1000); // Simulate retrieval delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Customer thread interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred in Customer thread: " + e.getMessage());
        }
    }
}
