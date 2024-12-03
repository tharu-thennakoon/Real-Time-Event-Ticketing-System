package org.example;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        TicketPool ticketPool = new TicketPool(100);
        TicketingSystem ticketingSystem = new TicketingSystem(ticketPool);
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
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 8.");
                scanner.next();
                continue;
            }

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    config.setConfiguration();
                    break;
                case "2":
                    ticketingSystem.startSystem(2, 3, config.getTicketReleaseRate(), config.getCustomerRetrievalRate());
                    break;
                case "3":
                    ticketingSystem.stopSystem();
                    break;
                case "4":
                    System.out.println("Current Tickets in Pool: " + ticketPool.getCurrentTicketCount());
                    break;
                case "5":
                    config.saveConfiguration("config.txt"); // Save configuration to default file
                    break;
                case "6":
                    config.loadConfiguration("config.txt"); // Load configuration from default file
                    break;
                case "7":
                    config.setConfiguration();
                    break;
                case "8":
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input! Please enter a valid choice.");
            }
        }
    }
}
