package org.example;


public class Customer implements Runnable {
   private final TicketPool ticketPool;
   private final int customerRetrievalRate;

   public Customer( TicketPool ticketPool,  int customerRetrievalRate) {
       this.ticketPool = ticketPool;
       this.customerRetrievalRate = customerRetrievalRate;
   }

    public void run(){
       while (!Thread.currentThread().isInterrupted()) {
           ticketPool.removeTickets(customerRetrievalRate);
           try {
               Thread.sleep(1000);
           }

           catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
    }
}
