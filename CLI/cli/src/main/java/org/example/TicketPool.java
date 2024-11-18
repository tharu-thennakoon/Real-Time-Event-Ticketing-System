package org.example;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class TicketPool {
    private final BlockingQueue<Integer> tickets;
    private final int maxCapacity;
    private int totalTickets;
    private  int totalTicketsProcessed;
    private volatile boolean isRunning;

    public TicketPool( int totalTicket, int maxCapacity) {
        this.tickets = new LinkedBlockingQueue<>(maxCapacity);
        this.maxCapacity = maxCapacity;
        this.totalTickets = totalTicket;
        this.totalTicketsProcessed = 0;
        this.isRunning = true;
    }

    public synchronized boolean addTicket(int ticketId) {
        if (!isRunning || totalTicketsProcessed >= totalTickets){
            return false;
        }
        try {
            tickets.put(ticketId);
            totalTicketsProcessed++;
            return true;
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public Integer removeTicket() {
        try{
            return isRunning ? tickets.poll(1, TimeUnit.SECONDS) : null;
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return null;
        }
    }



    public void stop(){
        isRunning = false;
    }

    public int getAvailableTickets(){
        return tickets.size();
    }

    public int getTotalTicketsProcessed(){
        return totalTicketsProcessed;
    }

    public boolean isRunning(){
        return isRunning;
    }

}