package org.example;

import java.io.*;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public void setConfiguration() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter Total Tickets: ");
            totalTickets = validateInput(scanner.nextInt());

            System.out.print("Enter Ticket Release Rate: ");
            ticketReleaseRate = validateInput(scanner.nextInt());

            System.out.print("Enter Customer Retrieval Rate: ");
            customerRetrievalRate = validateInput(scanner.nextInt());

            System.out.print("Enter Maximum Ticket Capacity: ");
            maxTicketCapacity = validateInput(scanner.nextInt());

            if (totalTickets > maxTicketCapacity) {
                throw new IllegalArgumentException("Total tickets cannot exceed maximum ticket capacity.");
            }

            System.out.println("Configuration set successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void viewConfiguration() {
        System.out.println("\n=== Current Configuration ===");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Maximum Ticket Capacity: " + maxTicketCapacity);
    }

    // Save configuration to a text file
    public void saveConfiguration(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Total Tickets: " + totalTickets + "\n");
            writer.write("Ticket Release Rate: " + ticketReleaseRate + "\n");
            writer.write("Customer Retrieval Rate: " + customerRetrievalRate + "\n");
            writer.write("Maximum Ticket Capacity: " + maxTicketCapacity + "\n");
            System.out.println("Configuration saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving configuration to file: " + e.getMessage());
        }
    }

    // Load configuration from a text file
    public void loadConfiguration(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                switch (parts[0].trim()) {
                    case "Total Tickets":
                        totalTickets = Integer.parseInt(parts[1]);
                        break;
                    case "Ticket Release Rate":
                        ticketReleaseRate = Integer.parseInt(parts[1]);
                        break;
                    case "Customer Retrieval Rate":
                        customerRetrievalRate = Integer.parseInt(parts[1]);
                        break;
                    case "Maximum Ticket Capacity":
                        maxTicketCapacity = Integer.parseInt(parts[1]);
                        break;
                }
            }
            System.out.println("Configuration loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading configuration from file: " + e.getMessage());
        }
    }

    private int validateInput(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be positive.");
        }
        return value;
    }

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
}

