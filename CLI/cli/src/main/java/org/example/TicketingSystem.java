package org.example;
import java.util.List;
import java.util.ArrayList;

public class TicketingSystem {
    private final Configuration config;
    private TicketPool ticketPool;
    private List<Thread> vendorThreads;
    public List<Thread> customerThreads;
    public List<Vendor> vendors;
    private List<Customer> customers;
    private boolean isRunning;

    public TicketingSystem( Configuration config) {
        this.config = config;
        this.vendorThreads = new ArrayList<>();
        this.customerThreads = new ArrayList<>();
        this.vendors = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isRunning = false;
    }

    public void start() {
        if (isRunning) {
            System.out.println("Ticketing system is already running");
            return;
        }

        ticketPool = new TicketPool(config.getMaxTicketCapacity(),config.getTotalTickets());
        isRunning = true;

        for (int i=0; i <3; i++){
            Vendor vendor = new Vendor(i + 1 , ticketPool, config.getTicketReleaseRate());
            vendors.add(vendor);
            Thread vendorthread = new Thread(vendor);
            vendorThreads.add(vendorthread);
            vendorthread.start();
        }

        for (int i = 0; i < 5; i++){
            Customer customer = new Customer(i + 1 , ticketPool, config.getTicketReleaseRate());
            customers.add(customer);
            Thread customerthread = new Thread(customer);
            customerThreads.add(customerthread);
            customerthread.start();
        }
    }

    public void stop() {
        if(!isRunning){
            System.out.println("System is not running! ");
            return;
        }

        ticketPool.stop();
        isRunning = false;

        for(Thread thread : vendorThreads){
            try{
                thread.join();
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        for(Thread thread : customerThreads){
            try {
                thread.join();

            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        vendorThreads.clear();
        customerThreads.clear();
        vendors.clear();
        customers.clear();
    }

    public void displayStatus(){
        if (ticketPool == null) {
            System.out.println("System has not been started yet!");
            return;
        }

        System.out.println("Ticketing system status");
        System.out.println("Running: " + isRunning);
        System.out.println("Available Tickets: " + ticketPool.getAvailableTickets());
        System.out.println("Total Tickets Processed : " + ticketPool.getTotalTicketsProcessed());

        System.out.println("\nCustomer Statistics: ");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println("Customer " + (i + 1) + "purchased" +
                    customers.get(i).getTicketsPurchased() + "tickets");
        }

        System.out.println("==========================\n");
    }

    public boolean isRunning() {
        return isRunning;
    }
}
