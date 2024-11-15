package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();
        TicketingSystem system = new TicketingSystem(config);

        System.out.println("Current System Parameters");
        displayCurrentConfiguration(config);

        while (true) {
            System.out.println("     Welcome to Real-Time Event Ticketing System      ");
            System.out.println("System Menu:");
            System.out.println("1. Configure System");
            System.out.println("2. Start Ticketing System");
            System.out.println("3. Stop Ticketing System");
            System.out.println("4. Display Status");
            System.out.println("5. Save Configuration");
            System.out.println("6. Load Configuration");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    configureSystem(scanner, config);
                    break;
                case 2:
                    system.start();
                    break;
                case 3:
                    system.stop();
                    break;
                case 4:
                    system.displayStatus();
                    break;
                case 5:
                    saveConfiguration(config);
                    break;
                case 6:
                    loadConfiguration(config);
                case 7:
                    if (system.isRunning()){
                        system.stop();
                    }
                    System.out.println("Thank You For Using Event Ticketing System.Good Bye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");

            }

        }
    }

    private static void displayCurrentConfiguration(Configuration config) {
        System.out.println("\n>>>>  Current Configuration Details <<<<");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("Total Tickets                : %d\n" , config.getTotalTickets());
        System.out.printf("Ticket Release Rate          : %d tickets/second\n" , config.getTicketReleaseRate());
        System.out.printf("Customer retrieval Rate      : %d tickets/second\n" , config.getCustomerRetrievalRate());
        System.out.printf("Maximum Ticket Capasity      : %d\n", config.getMaxTicketCapacity());
        System.out.println("----------------------------------------------------------------------");
    }

    private static void configureSystem(Scanner scanner, Configuration config) {
        System.out.println("Enter total number of tickets : ");
        int totalTickets = scanner.nextInt();

        System.out.println("Enter ticket release rate(per second) : ");
        int releaseRate = scanner.nextInt();

        System.out.println("Enter customer retrieval rate (per second) : ");
        int retrievalRate = scanner.nextInt();

        System.out.println("Enter maximum ticket capacity : ");
        int maxCapacity = scanner.nextInt();

        config.setConfiguration(totalTickets, releaseRate, retrievalRate, maxCapacity);
        System.out.println("Configuration updated successfully.");
    }

    private static void saveConfiguration(Configuration config) {
        try {
            config.saveToFile();
            System.out.println("Configuration saved successfully.");

        }
        catch (IOException e) {
            System.out.println("Error saving configuration." + e.getMessage());
        }
    }

    private static void loadConfiguration(Configuration config) {
        try{
            config.loadFromFile();
            System.out.println("Configuration loaded successfully.");
            System.out.println("Total Tickets : " + config.getTotalTickets());
            System.out.println("Ticket release rate : " + config.getTicketReleaseRate());
            System.out.println("Customer retrieval rate : " + config.getCustomerRetrievalRate());
            System.out.println("Maximum Ticket Capacity : " + config.getMaxTicketCapacity());
        }

        catch (IOException e) {
            System.out.println("Error loading configuration." + e.getMessage());
        }
    }


}