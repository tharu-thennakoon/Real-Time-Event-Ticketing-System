package com.oopcw.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class TicketPoolService {
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int maxCapacity;
    private int nextTicketId = 1;
    private int totalTicketsIssued = 0;

    public TicketPoolService(@Value("${ticket.pool.max-capacity}") int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be greater than zero.");
        }
        this.maxCapacity = maxCapacity;
    }

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

    public synchronized int removeTicket() {
        try {
            while (tickets.isEmpty()) {
                wait(); // Wait until tickets are available
            }
            int ticketId = tickets.poll();
            notifyAll();
            System.out.println("Ticket retrieved: " + ticketId + ". Remaining pool size: " + tickets.size());
            return ticketId;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Ticket removal interrupted: " + e.getMessage());
            return -1; // Indicating failure to remove ticket
        }
    }

    public synchronized int getCurrentTicketCount() {
        return tickets.size();
    }

    public synchronized int getTotalTicketsIssued() {
        return totalTicketsIssued;
    }
}
