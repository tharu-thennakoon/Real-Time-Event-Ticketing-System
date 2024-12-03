package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Integer>  tickets = new LinkedList<>();
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int count) {
        while (tickets.size() + count > maxCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (int i=0; i < count; i++) {
            tickets.add(1);
        }

        System.out.println(count + " tickets added to the pool.");
        notifyAll();
    }

    public synchronized void removeTickets(int count) {
        while (tickets.size() < count) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        for (int i = 0; i < count; i++) {
            tickets.poll();
        }
        System.out.println(count + " tickets removed from the pool.");
        notifyAll();
    }

    public synchronized int getCurrentTicketCount() {
        return tickets.size();
    }
}