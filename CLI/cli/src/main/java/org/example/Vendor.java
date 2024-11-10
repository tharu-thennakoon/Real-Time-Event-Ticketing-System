package org.example;


public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;
    private final int vendorId;
    private int ticketsReleased;

    public Vendor(int vendorId , TicketPool ticketPool , int releaseRate) {
        this.vendorId = vendorId;
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
                    System.out.println("vendor " + vendorId + " released ticket   " + ticketsReleased);
                    Thread.sleep(1000 / releaseRate);
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
}
