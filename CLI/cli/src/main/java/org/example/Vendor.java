package org.example;

import java.time.LocalDateTime; // Importing the LocalDateTime class to get the current date and time
import java.time.format.DateTimeFormatter; // Importing the DateTimeFormatter to format the date and time

// The Vendor class implements the Runnable interface to allow it to run in a separate thread
public class Vendor implements Runnable {
    private final TicketPool ticketPool; // Reference to the shared TicketPool object
    private final int ticketReleaseRate; // Rate at which the vendor adds tickets (in seconds)
    private final int maxTickets; // The maximum number of tickets that can be issued

    // Constructor to initialize the Vendor with a TicketPool, ticket release rate, and max ticket limit
    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int maxTickets) {
        this.ticketPool = ticketPool; // Set the TicketPool reference
        this.ticketReleaseRate = ticketReleaseRate; // Set the ticket release rate
        this.maxTickets = maxTickets; // Set the maximum tickets that can be issued
    }

    // The run method is executed when the Vendor thread starts
    @Override
    public void run() {
        // Create a DateTimeFormatter to format the date and time output
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            // The loop continues until the thread is interrupted or all tickets have been issued
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (ticketPool) { // Synchronize on the ticket pool to avoid race conditions
                    // Check if the total number of tickets issued has reached or exceeded the maximum limit
                    if (ticketPool.getTotalTicketsIssued() >= maxTickets) {
                        // If the maximum number of tickets is issued, stop the vendor thread
                        System.out.println(LocalDateTime.now().format(formatter) + " - " + Thread.currentThread().getName() + " has stopped. All tickets issued.");
                        return; // Exit the run method, which stops the vendor thread
                    }
                }
                // Add one ticket to the pool
                ticketPool.addTickets(1);
                // Print the log message showing the vendor added a ticket
                System.out.println(LocalDateTime.now().format(formatter) + " - " + Thread.currentThread().getName() + " added a ticket.");
                // Simulate a delay between ticket releases
                Thread.sleep(ticketReleaseRate * 1000); // Sleep for the defined rate, converted to milliseconds
            }
        } catch (InterruptedException e) {
            // Handle the InterruptedException, which occurs when the thread is interrupted
            Thread.currentThread().interrupt(); // Reset the interrupt flag
            System.out.println("Vendor thread interrupted: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other exceptions and print the error message
            System.out.println("An error occurred in Vendor thread: " + e.getMessage());
        }
    }
}
