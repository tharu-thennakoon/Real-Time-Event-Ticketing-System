package org.example;

import com.google.gson.Gson;
import java.io.*;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int numberOfVendors;

    // Method to configure the system
    public void setConfiguration() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter Total Tickets: ");
            totalTickets = validatePositiveInput(scanner.nextInt());

            System.out.print("Enter Ticket Release Rate: ");
            ticketReleaseRate = validatePositiveInput(scanner.nextInt());

            System.out.print("Enter Customer Retrieval Rate: ");
            customerRetrievalRate = validatePositiveInput(scanner.nextInt());

            System.out.print("Enter Maximum Ticket Capacity: ");
            maxTicketCapacity = validatePositiveInput(scanner.nextInt());

            if (totalTickets > maxTicketCapacity) {
                throw new IllegalArgumentException("Total tickets cannot exceed maximum ticket capacity.");
            }

            System.out.print("Enter Number of Vendors: ");
            numberOfVendors = validatePositiveInput(scanner.nextInt());

            System.out.println("Configuration set successfully!");
            saveConfiguration("config.json");
            System.out.println("Configuration saved to 'config.json'.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    // Validate input to ensure it's a positive number
    private int validatePositiveInput(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be a positive number.");
        }
        return value;
    }

    // View the current configuration
    public void viewConfiguration() {
        System.out.println("\n=== Current Configuration ===");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Maximum Ticket Capacity: " + maxTicketCapacity);
        System.out.println("Number of Vendors: " + numberOfVendors);
    }

    // Save configuration to a JSON file using Gson
    public void saveConfiguration(String fileName) {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Load configuration from a JSON file using Gson
    public boolean loadConfiguration(String fileName) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(fileName)) {
            Configuration config = gson.fromJson(reader, Configuration.class);
            this.totalTickets = config.totalTickets;
            this.ticketReleaseRate = config.ticketReleaseRate;
            this.customerRetrievalRate = config.customerRetrievalRate;
            this.maxTicketCapacity = config.maxTicketCapacity;
            this.numberOfVendors = config.numberOfVendors;
            return true; // Successfully loaded
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            return false; // Loading failed
        }
    }

    // Getters for configuration values
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
