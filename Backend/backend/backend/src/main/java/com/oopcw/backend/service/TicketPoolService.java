package com.oopcw.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class TicketPoolService {

    private Queue<Integer> tickets; // Ticket pool (Queue)
    private int maxCapacity; // Maximum capacity of the ticket pool
    private int nextTicketId; // Generates unique ticket IDs
    private int totalTicketsIssued; // Tracks total tickets issued

    // Constructor to inject default maxCapacity value
    public TicketPoolService(@Value("${ticket.pool.max-capacity}") int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be greater than zero.");
        }
        this.maxCapacity = maxCapacity;
        this.tickets = new LinkedList<>();
        this.nextTicketId = 1; // Initialize ticket ID generator
        this.totalTicketsIssued = 0; // Reset issued tickets count
    }

    // Reinitialize the ticket pool (for restarting simulations)
    public synchronized void initialize(int newMaxCapacity) {
        if (newMaxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be greater than zero.");
        }
        this.maxCapacity = newMaxCapacity;
        this.tickets = new LinkedList<>(); // Reset ticket queue
        this.nextTicketId = 1; // Reset ticket ID generator
        this.totalTicketsIssued = 0; // Reset issued tickets count
        System.out.println("Ticket pool reinitialized with max capacity: " + newMaxCapacity);
    }

    // Method to add tickets to the pool (called by vendors)
    public synchronized void addTickets(int count) {
        try {
            while (tickets.size() >= maxCapacity || totalTicketsIssued >= maxCapacity) {
                System.out.println(Thread.currentThread().getName() + " is waiting to add tickets...");
                wait(); // Wait until space is available
            }

            int ticketsToAdd = Math.min(count, maxCapacity - totalTicketsIssued);
            for (int i = 0; i < ticketsToAdd; i++) {
                tickets.add(nextTicketId++); // Add a new ticket ID to the pool
                totalTicketsIssued++;
            }

            System.out.println(Thread.currentThread().getName() + " added " + ticketsToAdd +
                    " ticket(s). Current pool size: " + tickets.size());
            notifyAll(); // Notify waiting threads
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Ticket addition interrupted: " + e.getMessage());
        }
    }

    // Method for customers to remove tickets from the pool
    public synchronized int removeTicket() {
        try {
            while (tickets.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " is waiting for tickets...");
                wait(); // Wait until tickets are available
            }

            int ticketId = tickets.poll(); // Remove a ticket from the pool
            System.out.println(Thread.currentThread().getName() +
                    " retrieved Ticket ID: " + ticketId + ". Remaining pool size: " + tickets.size());
            notifyAll(); // Notify waiting threads
            return ticketId;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Ticket removal interrupted: " + e.getMessage());
            return -1;  // Indicating failure to remove ticket
        }
    }

    // Get the current size of the ticket pool
    public synchronized int getCurrentTicketCount() {
        System.out.println("Current ticket count: " + tickets.size());
        return tickets.size();
    }

    // Get the total number of tickets issued
    public synchronized int getTotalTicketsIssued() {
        System.out.println("Total tickets issued: " + totalTicketsIssued);
        return totalTicketsIssued;
    }

    // Get the maximum capacity of the pool
    public int getMaxCapacity() {
        return maxCapacity;
    }
}
