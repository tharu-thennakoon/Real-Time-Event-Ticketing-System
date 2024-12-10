package com.oopcw.backend.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class TicketPoolService {

    private Queue<Integer> tickets;  // Ticket pool (Queue)
    private int maxCapacity;         // Maximum capacity of the ticket pool
    private int nextTicketId;        // Generates unique ticket IDs
    private int totalTicketsIssued;  // Tracks total tickets issued
    private boolean simulationRunning; // Flag to check if simulation is running
    private final List<String> logs;  // Stores logs for real-time analytics

    public TicketPoolService() {
        this.maxCapacity = 100; // Default capacity
        this.tickets = new LinkedList<>();
        this.logs = new ArrayList<>();
        initialize(this.maxCapacity);
    }

    // Initialize the ticket pool
    public synchronized void initialize(int newMaxCapacity) {
        this.maxCapacity = newMaxCapacity;
        this.tickets = new LinkedList<>();
        this.nextTicketId = 1; // Reset ticket ID
        this.totalTicketsIssued = 0; // Reset issued tickets count
        this.simulationRunning = false;
        logs.clear();
        log("Ticket pool initialized with max capacity: " + newMaxCapacity);
    }

    // Add tickets to the pool
    public synchronized void addTickets(int count) {
        try {
            while (tickets.size() >= maxCapacity) {
                log(Thread.currentThread().getName() + " is waiting to add tickets...");
                wait(); // Wait if the pool is full
            }

            for (int i = 0; i < count; i++) {
                tickets.add(nextTicketId++);  // Add a new ticket ID to the pool
                totalTicketsIssued++;
                log(Thread.currentThread().getName() + " added Ticket ID: " + (nextTicketId - 1));
            }

            notifyAll(); // Notify all waiting threads
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("Ticket addition interrupted: " + e.getMessage());
        }
    }

    // Remove tickets from the pool
    public synchronized int removeTicket() {
        try {
            while (tickets.isEmpty()) {
                log(Thread.currentThread().getName() + " is waiting for tickets...");
                wait();  // Wait if the pool is empty
            }

            int ticketId = tickets.poll(); // Remove a ticket from the pool
            log(Thread.currentThread().getName() + " retrieved Ticket ID: " + ticketId);
            notifyAll(); // Notify all waiting threads
            return ticketId;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("Ticket retrieval interrupted: " + e.getMessage());
            return -1;  // Indicate failure to retrieve a ticket
        }
    }

    // Start the simulation
    public synchronized String startSimulation() {
        if (simulationRunning) return "Simulation is already running.";
        simulationRunning = true;
        log("Simulation started.");
        return "Simulation started.";
    }

    // Stop the simulation
    public synchronized String stopSimulation() {
        if (!simulationRunning) return "Simulation is not running.";
        simulationRunning = false;
        log("Simulation stopped.");
        return "Simulation stopped.";
    }

    // Reset the simulation
    public synchronized String resetSimulation() {
        if (simulationRunning) stopSimulation(); // Stop if running
        initialize(this.maxCapacity);  // Reinitialize ticket pool
        log("Simulation reset.");
        return "Simulation reset.";
    }

    // Get analytics (e.g., total tickets issued, pool size)
    public synchronized String getAnalytics() {
        return "Total Tickets Issued: " + totalTicketsIssued + "\n" +
               "Current Pool Size: " + tickets.size() + "\n" +
               "Max Pool Capacity: " + maxCapacity;
    }

    // Get logs
    public synchronized String getLogs() {
        return String.join("\n", logs);
    }

    // Helper method to log messages
    private void log(String message) {
        logs.add(message);
        System.out.println(message); // Also print to console
    }

    public synchronized int getTotalTicketsIssued() {
        return totalTicketsIssued;
    }

    public synchronized int getCurrentTicketCount() {
        return tickets.size();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
