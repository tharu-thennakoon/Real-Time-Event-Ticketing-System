package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketingSystem {
    private final TicketPool ticketPool;
    private final List<Thread> threads = new ArrayList<>();

    public TicketingSystem(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void startSystem(int vendors, int customers, int ticketReleaseRate, int customerRetrievalRate, int maxTickets) {
        // Start vendor threads
        for (int i = 0; i < vendors; i++) {
            Thread vendorThread = new Thread(new Vendor(ticketPool, ticketReleaseRate, maxTickets), "Vendor-" + i);
            threads.add(vendorThread);
            vendorThread.start();
        }

        // Start customer threads
        for (int i = 0; i < customers; i++) {
            Thread customerThread = new Thread(new Customer(ticketPool, customerRetrievalRate), "Customer-" + i);
            threads.add(customerThread);
            customerThread.start();
        }

        System.out.println("System started with " + vendors + " vendors and " + customers + " customers.");

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();  // Wait for each thread to finish
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All tickets have been issued.");
        // After all tickets are issued, prompt the user to exit
        askUserToExit();
    }

    // Ask the user if they want to exit
    private void askUserToExit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Simulation completed. Do you want to exit? (yes/no): ");
        String exitChoice = scanner.nextLine().trim().toLowerCase();

        if (exitChoice.equals("yes")) {
            System.out.println("Exiting the system. Goodbye!");
            System.exit(0); // Exit the system
        } else {
            System.out.println("Returning to main menu...");
        }
    }

    public void stopSystem() {
        threads.forEach(Thread::interrupt);
        System.out.println("System stopped.");
    }
}
