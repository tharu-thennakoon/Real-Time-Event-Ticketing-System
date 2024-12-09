package com.oopcw.backend.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int maxCapacity = 100; // Pool size
    private int nextTicketId = 1; // Unique ticket ID
    private int totalTicketsIssued = 0; // Tracks the total number of tickets issued

    public synchronized void addTickets(int count) {
        try {
            while (tickets.size() >= maxCapacity || totalTicketsIssued >= maxCapacity) {
                wait(); // Wait until space is available
            }

            int ticketsToAdd = Math.min(count, maxCapacity - totalTicketsIssued);
            for (int i = 0; i < ticketsToAdd; i++) {
                tickets.add(nextTicketId++);
                totalTicketsIssued++;
            }
            System.out.println(ticketsToAdd + " ticket(s) added. Pool size: " + tickets.size());
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Ticket addition interrupted: " + e.getMessage());
        }
    }

    public synchronized int getTotalTicketsIssued() {
        return totalTicketsIssued;
    }
}
