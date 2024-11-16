package org.example;

import java.io.IOException;
import java.util.List;
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
                    configureSystem(scanner, vendors , customers);
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
        System.out.printf("Ticket Release Rate          : %d tickets/minute\n" , config.getTicketReleaseRate());
        System.out.printf("Customer retrieval Rate      : %d tickets/minute\n" , config.getCustomerRetrievalRate());
        System.out.printf("Maximum Ticket Capasity      : %d\n", config.getMaxTicketCapacity());
        System.out.println("----------------------------------------------------------------------");
    }

    private static void configureSystem(Scanner scanner, List<Vendor> vendors, List<Customer> customers) {
        boolean continueOption1 = true;
        while (continueOption1) {
            System.out.println("\n-----------Manage vendors and Custormers-------------------");
            System.out.println("1. Add Vendor");
            System.out.println("2. Add Customer");
            System.out.println("3. Go to main menu");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addVendor(scanner, vendors);
                    break;
                case 2:
                    break;
                case 3:
                    continueOption1 = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1,2 or 3");
            }
        }
    }

    private static void addVendor(Scanner scanner, List<Vendor> vendors) {
        boolean addMoreVendors = true;
        while (addMoreVendors) {
            System.out.println("\n>>>>Enter vendor details<<<<");
            System.out.println("Enter vendor name: ");
            String vendorName = scanner.next();

            System.out.println("Enter vendor ID: ");
            String vendorID = scanner.next();

            System.out.println("Enter vendor Email: ");
            String vendorEmail = scanner.next();

            System.out.println("Ticket Release Rate(per minute): ");
            int releaseRate = scanner.nextInt();

            TicketPool ticketPool = new TicketPool();

            Vendor vendor = new Vendor(vendorName , vendorID, vendorEmail, releaseRate, ticketPool);
            vendors.add(vendor);

            System.out.println("Do you want to add more vendors? (yes/no): ");
            String response = scanner.next();
            addMoreVendors = response.equalsIgnoreCase("yes");
        }

    }

    private static void addCustomer(Scanner scanner, List<Customer> customers, TicketPool ticketPool) {
        boolean addMoreCustomers = true;
        while (addMoreCustomers) {
            System.out.println("\n>>>>Enter customer details<<<<");
            System.out.println("Enter customer name: ");
            String customerName = scanner.next();

            System.out.println("Enter customer ID: ");
            String customerID = scanner.next();

            System.out.println("Enter customer Email: ");
            String customerEmail = scanner.next();

            System.out.println("Ticket Retrieval Rate(per minute): ");
            int retrievalRate = scanner.nextInt();

            Customer customer = new Customer(customerName, customerID, customerEmail, retrievalRate, ticketPool);
            customers.add(customer);

            System.out.println("Do you want to add more customers? (yes/no): ");
            String response = scanner.next();
            addMoreCustomers = response.equalsIgnoreCase("yes");
        }
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