package org.example;

// Import required libraries
import com.google.gson.Gson; // For JSON serialization and deserialization
import java.io.*; // For file operations
import java.util.Scanner; // For user input

// Configuration class to manage system settings
public class Configuration {
    // Private fields to store configuration parameters
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int numberOfVendors;

    // Method to configure the system via user input
    public void setConfiguration() {
        // Create a Scanner instance for reading user input
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user for the total number of tickets
            System.out.print("Enter Total Tickets: ");
            totalTickets = validatePositiveInput(scanner.nextInt()); // Validate input

            // Prompt user for ticket release rate
            System.out.print("Enter Ticket Release Rate: ");
            ticketReleaseRate = validatePositiveInput(scanner.nextInt());

            // Prompt user for customer retrieval rate
            System.out.print("Enter Customer Retrieval Rate: ");
            customerRetrievalRate = validatePositiveInput(scanner.nextInt());

            // Prompt user for maximum ticket capacity
            System.out.print("Enter Maximum Ticket Capacity: ");
            maxTicketCapacity = validatePositiveInput(scanner.nextInt());

            // Check if total tickets exceed the maximum capacity
            if (totalTickets > maxTicketCapacity) {
                throw new IllegalArgumentException("Total tickets cannot exceed maximum ticket capacity.");
            }

            // Prompt user for the number of vendors
            System.out.print("Enter Number of Vendors: ");
            numberOfVendors = validatePositiveInput(scanner.nextInt());

            // Display success message
            System.out.println("Configuration set successfully!");

            // Save the configuration to a file
            saveConfiguration("config.json");
            System.out.println("Configuration saved to 'config.json'.");

        } catch (IllegalArgumentException e) {
            // Handle specific input validation errors
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Method to validate that a number is positive
    private int validatePositiveInput(int value) {
        // If the value is not positive, throw an exception
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be a positive number.");
        }
        return value; // Return the validated value
    }

    // Method to display the current configuration
    public void viewConfiguration() {
        System.out.println("\n=== Current Configuration ===");
        // Print each configuration parameter
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Maximum Ticket Capacity: " + maxTicketCapacity);
        System.out.println("Number of Vendors: " + numberOfVendors);
    }

    // Method to save the configuration to a JSON file
    public void saveConfiguration(String fileName) {
        // Create a Gson instance for JSON serialization
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(fileName)) {
            // Write the current object to the specified file as JSON
            gson.toJson(this, writer);
        } catch (IOException e) {
            // Handle file writing errors
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Method to load the configuration from a JSON file
    public boolean loadConfiguration(String fileName) {
        // Create a Gson instance for JSON deserialization
        Gson gson = new Gson();
        try (Reader reader = new FileReader(fileName)) {
            // Deserialize the JSON file into a Configuration object
            Configuration config = gson.fromJson(reader, Configuration.class);

            // Copy the loaded configuration values to the current object
            this.totalTickets = config.totalTickets;
            this.ticketReleaseRate = config.ticketReleaseRate;
            this.customerRetrievalRate = config.customerRetrievalRate;
            this.maxTicketCapacity = config.maxTicketCapacity;
            this.numberOfVendors = config.numberOfVendors;

            return true; // Return true if loading was successful
        } catch (IOException e) {
            // Handle file reading errors
            System.out.println("Error loading configuration: " + e.getMessage());
            return false; // Return false if loading failed
        }
    }

    // Getter methods for accessing configuration parameters
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getNumberOfVendors() {
        return numberOfVendors;
    }
}
