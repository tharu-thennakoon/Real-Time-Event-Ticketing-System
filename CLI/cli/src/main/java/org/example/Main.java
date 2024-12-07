package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        TicketPool ticketPool = new TicketPool(100); // Max pool size
        TicketingSystem ticketingSystem = new TicketingSystem(ticketPool);
        Scanner scanner = new Scanner(System.in);

        // First, load configuration from file
        System.out.println("Welcome to Real-Time Event Ticketing System");
        if (!config.loadConfiguration("config.json")) {
            System.out.println("No saved configuration found. Please set a new configuration.");
            config.setConfiguration();
        } else {
            System.out.println("Configuration loaded from 'config.json'.");
        }

        // Main Menu
        while (true) {
            try {
                System.out.println("\n>>> System Menu <<<");
                System.out.println("1. View Current Configuration");
                System.out.println("2. Set System Configuration");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        // Option 1: View current configuration and prompt to simulate
                        config.viewConfiguration();
                        System.out.print("Do you want to simulate? (yes/no): ");
                        String simulateChoice = scanner.nextLine().trim().toLowerCase();
                        if (simulateChoice.equals("yes")) {
                            // Start simulation
                            ticketingSystem.startSystem(
                                    config.getNumberOfVendors(), // Vendors from configuration
                                    3, // Number of customers
                                    config.getTicketReleaseRate(),
                                    config.getCustomerRetrievalRate(),
                                    config.getTotalTickets()
                            );
                        } else {
                            System.out.println("Simulation skipped. Returning to menu.");
                        }
                        break;

                    case "2":
                        // Option 2: Set system configuration
                        config.setConfiguration(); // Set configuration
                        System.out.print("Do you want to simulate? (yes/no): "); // Ask if they want to simulate
                        String simulateAfterConfig = scanner.nextLine().trim().toLowerCase();
                        if (simulateAfterConfig.equals("yes")) {
                            // Start simulation if the user selects yes
                            ticketingSystem.startSystem(
                                    config.getNumberOfVendors(), // Vendors from configuration
                                    3, // Number of customers
                                    config.getTicketReleaseRate(),
                                    config.getCustomerRetrievalRate(),
                                    config.getTotalTickets()
                            );
                        } else {
                            System.out.println("Returning to menu.");
                        }
                        break;

                    case "3":
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
