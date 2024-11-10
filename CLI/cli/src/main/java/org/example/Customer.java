package org.example;


public class Customer implements Runnable {
   private final TicketPool ticketPool;
   private final int retrievalRate;
   private final int customerId;
   private int ticketsPurchased;

   public Customer(int customerId, TicketPool ticketPool, int retrievalRate) {
       this.customerId = customerId;
       this.ticketPool = ticketPool;
       this.retrievalRate = retrievalRate;
       this.ticketsPurchased = 0;
   }

   @Override
    public void run() {
       while (ticketPool.isRunning()){
           try {
               Integer ticket = ticketPool.removeTicket();
               if(ticket != null){
                   ticketsPurchased++;
                   System.out.println("Customer " + customerId + " purchased ticket " + ticket);
               }
               Thread.sleep(1000 / retrievalRate);
           }
           catch (InterruptedException e){
               Thread.currentThread().interrupt();
               break;
           }
       }
   }

   public int getTicketsPurchased() {
       return ticketsPurchased;
   }
}
