package org.example;


public class Vendor implements Runnable {
    private final int id;
    private final TicketPool ticketPool;
    private final Configuration config;
    private volatile boolean running;

    public Vendor( int id,  TicketPool ticketPool,  Configuration config) {
        this.id = id;
        this.ticketPool = ticketPool;
        this.config = config;
        this.running = true;
    }

    @Override
    public void run() {
        try{
            while (running) {
                Ticket ticket = new Ticket( ticketPool.getNextTicketId(),id );
                ticketPool.addTicket(ticket);
                System.out.println("Vendor" + id + "added ticket" + ticket.getId());
            }

        }
        catch (Exception e){
            Thread.currentThread().interrupt();
            System.out.println("Vendor" + id + "was interrupted");
        }
    }

    public void stop() {
        running = false;
    }
}
