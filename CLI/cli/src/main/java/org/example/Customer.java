package org.example;


public class Customer implements Runnable {
    private final int id;
    private final TicketPool ticketPool;
    private final Configuration config;
    private volatile boolean running;
    private int ticketsPurchased;

    public Customer(int id, TicketPool ticketPool, Configuration config) {
        this.id = id;
        this.ticketPool = ticketPool;
        this.config = config;
        this.running = true;
        this.ticketsPurchased = 0;
    }

    @Override
    public void run() {
        try {
            while (running) {
                Ticket ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    ticketsPurchased++;
                    System.out.println("Customer " + id + " purchased ticket " + ticket.getId() +
                            "from Vendor" + ticket.getVendorId());
                }

                Thread.sleep(config.getCustomerReleaseRate());
            }

        }
        catch (Exception e){
            Thread.currentThread().interrupt();
            System.out.println("Customer " + id + " has been interrupted");
        }
    }

    public void stop(){
        running = false;
    }

    public int getId(){
        return id;
    }
    public int getTicketsPurchased(){
        return ticketsPurchased;
    }
}
