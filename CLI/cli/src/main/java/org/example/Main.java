package org.example;

// Import necessary classes
import java.util.Scanner; // Scanner is used for user input

// Main class of the application
public class Main {
    public static void main(String[] args) {
        // Create an instance of Configuration class to manage system settings
        Configuration config = new Configuration();

        // Declare variables for ticket pool and ticketing system
        TicketPool ticketPool;
        TicketingSystem ticketingSystem = null;

        // Create a Scanner instance for reading user input
        Scanner scanner = new Scanner(System.in);

        // Display a welcome message
        System.out.println("Welcome to Real-Time Event Ticketing System");

        // Attempt to load configuration from a file
        if (!config.loadConfiguration("config.json")) {
            // If no configuration file is found, prompt the user to set a new one
            System.out.println("No saved configuration found. Please set a new configuration.");
            config.setConfiguration();
        } else {
            // Notify the user that the configuration has been successfully loaded
            System.out.println("Configuration loaded from 'config.json'.");
        }

        // Infinite loop for displaying the system menu and handling user actions
        while (true) {
            try {
                // Display the main menu
                System.out.println("\n>>> System Menu <<<");
                System.out.println("1. View Current Configuration");
                System.out.println("2. Set System Configuration");
                System.out.println("3. Start Simulation");
                System.out.println("4. Exit");

                // Prompt the user to make a choice
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine(); // Read user input as a string

                // Handle the user's choice using a switch-case
                switch (choice) {
                    case "1":
                        // Option 1: Display the current system configuration
                        config.viewConfiguration();
                        break;

                    case "2":
                        // Option 2: Allow the user to set or modify the system configuration
                        config.setConfiguration();
                        break;

                    case "3":
                        // Option 3: Start the ticketing system simulation
                        // Initialize the ticket pool with a maximum ticket capacity
                        ticketPool = new TicketPool(config.getMaxTicketCapacity());

                        // Create an instance of the ticketing system with the ticket pool
                        ticketingSystem = new TicketingSystem(ticketPool);

                        // Start the system simulation using parameters from the configuration
                        ticketingSystem.startSystem(
                                config.getNumberOfVendors(),     // Number of vendors
                                3,                              // Number of customers (fixed for now)
                                config.getTicketReleaseRate(),  // Ticket release rate
                                config.getCustomerRetrievalRate(), // Customer ticket retrieval rate
                                config.getTotalTickets()        // Total number of tickets to simulate
                        );

                        // Notify the user that the simulation has completed
                        System.out.println("Simulation completed. Returning to menu...");
                        break;

                    case "4":
                        // Option 4: Exit the system
                        System.out.println("Exiting the system. Goodbye!");
                        return; // Exit the program

                    default:
                        // Handle invalid menu options
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                // Catch and display any errors that occur during execution
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
