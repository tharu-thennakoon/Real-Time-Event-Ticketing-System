package org.example;

// Import required classes for date/time handling
import java.time.LocalDateTime; // Represents date and time
import java.time.format.DateTimeFormatter; // Formats date and time

// Customer class that implements the Runnable interface
// Represents a customer thread retrieving tickets from the ticket pool
public class Customer implements Runnable {
    // Reference to the shared TicketPool object
    private final TicketPool ticketPool;
    // Rate at which the customer retrieves tickets, in seconds
    private final int customerRetrievalRate;

    // Constructor to initialize the TicketPool and retrieval rate
    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool; // Assign shared TicketPool object
        this.customerRetrievalRate = customerRetrievalRate; // Set retrieval rate
    }

    // Override the run() method for the Runnable interface
    @Override
    public void run() {
        // Create a DateTimeFormatter for timestamp formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // Loop to continuously retrieve tickets until the thread is interrupted
            while (!Thread.currentThread().isInterrupted()) {
                // Attempt to retrieve a ticket from the TicketPool
                int ticketId = ticketPool.removeTicket();

                // Check if a valid ticket ID was retrieved (-1 indicates no ticket available)
                if (ticketId != -1) {
                    // Print the retrieval details with timestamp and thread name
                    System.out.println(LocalDateTime.now().format(formatter) + " - "
                            + Thread.currentThread().getName() + " retrieved Ticket ID " + ticketId);
                }

                // Pause the thread for a duration equal to the customer retrieval rate
                // This simulates the delay between ticket retrievals
                Thread.sleep(customerRetrievalRate * 1000);
            }
        } catch (InterruptedException e) {
            // Handle thread interruption
            Thread.currentThread().interrupt(); // Reset the interrupt status
            System.out.println("Customer thread interrupted: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            System.out.println("An error occurred in Customer thread: " + e.getMessage());
        }
    }
}
