package org.example;

// Import necessary classes
import java.util.LinkedList; // Provides a doubly-linked list implementation
import java.util.Queue; // Interface for a first-in-first-out (FIFO) collection

// Class to manage a shared pool of tickets
public class TicketPool {
    // A queue to hold tickets (FIFO structure)
    private final Queue<Integer> tickets = new LinkedList<>();
    // The maximum capacity of the ticket pool
    private final int maxCapacity;
    // A variable to generate unique ticket IDs
    private int nextTicketId = 1;
    // Tracks the total number of tickets issued so far
    private int totalTicketsIssued = 0;

    // Constructor to initialize the ticket pool with a maximum capacity
    public TicketPool(int maxCapacity) {
        // Ensure the maxCapacity is greater than zero
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be greater than zero.");
        }
        this.maxCapacity = maxCapacity; // Assign the maximum capacity
    }

    // Synchronized method to add tickets to the pool
    public synchronized void addTickets(int count) {
        try {
            // Wait if the pool is full or if the total tickets issued exceeds the max capacity
            while (tickets.size() >= maxCapacity || totalTicketsIssued >= maxCapacity) {
                wait(); // Release the lock and wait for space to become available
            }

            // Calculate the number of tickets that can be added (limited by remaining capacity)
            int ticketsToAdd = Math.min(count, maxCapacity - totalTicketsIssued);
            for (int i = 0; i < ticketsToAdd; i++) {
                tickets.add(nextTicketId++); // Add a new ticket with a unique ID
                totalTicketsIssued++; // Increment the total tickets issued
            }

            // Log the number of tickets added and the current pool size
            System.out.println(ticketsToAdd + " ticket(s) added. Pool size: " + tickets.size());
            notifyAll(); // Notify other waiting threads that tickets are available
        } catch (InterruptedException e) {
            // Handle interruptions gracefully and reset the interrupt flag
            Thread.currentThread().interrupt();
            System.out.println("Ticket addition interrupted: " + e.getMessage());
        }
    }

    // Synchronized method to remove a ticket from the pool
    public synchronized int removeTicket() {
        try {
            // Wait if there are no tickets available in the pool
            while (tickets.isEmpty()) {
                wait(); // Release the lock and wait for tickets to be added
            }

            // Remove and return the ticket at the front of the queue
            int ticketId = tickets.poll(); // Poll retrieves and removes the head of the queue
            notifyAll(); // Notify other threads that space is now available in the pool
            return ticketId; // Return the removed ticket ID
        } catch (InterruptedException e) {
            // Handle interruptions gracefully and reset the interrupt flag
            Thread.currentThread().interrupt();
            System.out.println("Ticket removal interrupted: " + e.getMessage());
            return -1; // Return -1 to indicate failure to remove a ticket
        }
    }

    // Synchronized method to get the current number of tickets in the pool
    public synchronized int getCurrentTicketCount() {
        return tickets.size(); // Return the size of the queue
    }

    // Synchronized method to get the total number of tickets issued
    public synchronized int getTotalTicketsIssued() {
        return totalTicketsIssued; // Return the total tickets issued
    }
}
