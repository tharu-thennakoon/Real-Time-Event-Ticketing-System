package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class TicketPool {
    private final Queue<Ticket> tickets;
    private int nextTicketId;

    public TicketPool(int capacity) {
        tickets = new LinkedList<>();
        nextTicketId = 1;

    }

    public synchronized Ticket removeTicket() throws InterruptedException{
        while (tickets.isEmpty()) {
            wait();
        }

        Ticket ticket = tickets.poll();
        notifyAll();
        return ticket;
    }

    public synchronized void addTicket(Ticket ticket) {
        tickets.add(ticket);
        notifyAll();
    }

    public synchronized int getNextTicketId() {
        return nextTicketId++;
    }

    public synchronized int getTicketCount() {
        return tickets.size();
    }
}
