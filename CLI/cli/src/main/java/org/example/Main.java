package org.example;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        TicketPool ticketPool = new TicketPool(100);
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("         Welcome to Real-Time Event Ticketing System       ");
            System.out.println(">>>System Menu<<<");
            System.out.println("1. Set System Configuration");
            System.out.println("2. Start Ticketing System");
            System.out.println("3. Stop Ticketing System");
            System.out.println("4. View Real-Time Status");
            System.out.println("5. Save Current Configuration to File");
            System.out.println("6. Load Current Configuration from File");
            System.out.println("7. Reset System Configuration");
            System.out.println("8. Help");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    config.setConfiguration();
                    break;
                case 2:
                    ticketingSystem.startSystem(2, 3, config.getTicketReleaseRate(), config.getCustomerRetrievalRate());
                    break;
                case 3:
                    ticketingSystem.stopStop();
                    break;
                case 4:
                    System.out.println("Current Tickets in Pool: " + ticketPool.getCurrentTicketCount());
                    break;
                case 5:
                    saveConfigurationToFile(Scanner, config);
                    break;
                case 6:
                    loadConfigurationFromFile(Scanner, config);
                    break;
                case 7:
                    config.setConfiguration();
                    break;
                case 8:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input! Please enter a valid choice.");

            }
        }
    }

    private static void saveConfigurationToFile(Scanner scanner, Configuration config) {
        config.saveConfiguration();
    }

    private static void loadConfigurationFromFile(Scanner scanner, Configuration config) {
        config.loadConfiguration();
    }

}