package org.example;


public class Vendor implements Runnable {
    private final String name;
    private final String id;
    private final String email;
    private final TicketPool ticketPool;
    private final int releaseRate;
    private int ticketsReleased;

    public Vendor(String name, String id, String email, TicketPool ticketPool, int releaseRate) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.ticketsReleased = 0;

    }

    @Override
    public void run() {
        while (ticketPool.isRunning()){
            try{
                if(ticketPool.addTicket(ticketsReleased + 1)){
                    ticketsReleased++;
                    System.out.println("vendor " + name + " released ticket   " + ticketsReleased);
                    // Adjust sleep time: 60 seconds divided by tickets per minute
                    Thread.sleep(20000 );
                }
                else{
                    break;
                }
            }

            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public int getTicketsReleased() {
        return ticketsReleased;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getReleaseRate() {
        return releaseRate;
    }
}
