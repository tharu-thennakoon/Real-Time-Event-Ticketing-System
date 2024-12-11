package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        TicketPool ticketPool;
        TicketingSystem ticketingSystem = null;
        Scanner scanner = new Scanner(System.in);

        // First, load configuration from file
        System.out.println("Welcome to Real-Time Event Ticketing System");
        if (!config.loadConfiguration("config.json")) {
            System.out.println("No saved configuration found. Please set a new configuration.");
            config.setConfiguration();
        } else {
            System.out.println("Configuration loaded from 'config.json'.");
        }

        while (true) {
            try {
                System.out.println("\n>>> System Menu <<<");
                System.out.println("1. View Current Configuration");
                System.out.println("2. Set System Configuration");
                System.out.println("3. Start Simulation");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        // Option 1: View current configuration
                        config.viewConfiguration();
                        break;

                    case "2":
                        // Option 2: Set system configuration
                        config.setConfiguration();
                        break;

                    case "3":
                        // Option 3: Start Simulation
                        ticketPool = new TicketPool(config.getMaxTicketCapacity());
                        ticketingSystem = new TicketingSystem(ticketPool);
                        ticketingSystem.startSystem(
                                config.getNumberOfVendors(),
                                3, // Number of customers
                                config.getTicketReleaseRate(),
                                config.getCustomerRetrievalRate(),
                                config.getTotalTickets()
                        );
                        System.out.println("Simulation completed. Returning to menu...");
                        break;

                    case "4":
                        // Exit the system
                        System.out.println("Exiting the system. Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
