package org.example;

// Import necessary classes
import java.util.ArrayList; // Allows us to use the ArrayList class
import java.util.List; // Interface for a list of objects

// Main class to manage the ticketing system
public class TicketingSystem {
    // Reference to the shared TicketPool object
    private final TicketPool ticketPool;
    // List to store all threads (vendors and customers)
    private final List<Thread> threads = new ArrayList<>();

    // Constructor to initialize the ticket pool
    public TicketingSystem(TicketPool ticketPool) {
        this.ticketPool = ticketPool; // Assign the shared TicketPool object
    }

    // Method to start the system with the given parameters
    public void startSystem(int vendors, int customers, int ticketReleaseRate,
                            int customerRetrievalRate, int totalTickets) {
        // Start vendor threads
        for (int i = 0; i < vendors; i++) {
            // Create a new vendor thread
            Thread vendorThread = new Thread(
                    new Vendor(ticketPool, ticketReleaseRate, totalTickets), // Vendor object
                    "Vendor-" + i // Name the thread as "Vendor-i"
            );
            threads.add(vendorThread); // Add the thread to the list
            vendorThread.start(); // Start the thread
        }

        // Start customer threads
        for (int i = 0; i < customers; i++) {
            // Create a new customer thread
            Thread customerThread = new Thread(
                    new Customer(ticketPool, customerRetrievalRate), // Customer object
                    "Customer-" + i // Name the thread as "Customer-i"
            );
            threads.add(customerThread); // Add the thread to the list
            customerThread.start(); // Start the thread
        }

        // Log the initialization of the system
        System.out.println("System started with " + vendors + " vendors and " + customers + " customers.");

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join(); // Wait for the current thread to complete execution
            } catch (InterruptedException e) {
                // If interrupted, reset the interrupt flag
                Thread.currentThread().interrupt();
            }
        }

        // Log the completion of the simulation
        System.out.println("Simulation completed. All tickets have been issued.");
    }
}
