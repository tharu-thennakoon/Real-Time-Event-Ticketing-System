package org.example;


public class Customer implements Runnable {
   private final String name;
   private final int id;
   private final String email;
   private final TicketPool ticketPool;
   private final int retrievalRate;
   private int ticketsPurchased;

   public Customer(String name, int id, String email, TicketPool ticketPool, int retrievalRate) {
       this.name = name;
       this.id = id;
       this.email = email;
       this.ticketPool = ticketPool;
       this.retrievalRate = retrievalRate;
       ticketsPurchased = 0;
   }

   @Override
    public void run() {
       while (ticketPool.isRunning()){
           try {
               Integer ticket = ticketPool.removeTicket();
               if(ticket != null){
                   ticketsPurchased++;
                   System.out.println("Customer " + name + " (ID: " + id + ") purchased ticket " + ticket);
               }
               // Adjust sleep time: 60 seconds divided by tickets per minute
               Thread.sleep(60000 / retrievalRate);
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

   public String getName() {
       return name;
   }

   public int getId() {
       return id;
   }

   public String getEmail() {
       return email;
   }

   public int getRetrievalRate() {
       return retrievalRate;
   }
}
