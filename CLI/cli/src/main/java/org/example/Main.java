package org.example;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        int totalTickets = config.getTotalTickets();
        int maxCapacity = config.getMaxTicketCapacity();
        TicketPool ticketPool = new TicketPool(totalTickets, maxCapacity);
        TicketingSystem system = new TicketingSystem(config);
        List<Vendor> vendors = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        System.out.println("Current System Parameters");
        displayCurrentConfiguration(config) ;

        while(true) {
            System.out.println("         Welcome to Real-Time Event Ticketing System       ");
            System.out.println(">>>System Menu<<<");
            System.out.println("1. Manage Vendors and Customers");
            System.out.println("2. Start Ticketing System");
            System.out.println("3. Stop Ticketing System");
            System.out.println("4. View System Overview");
            System.out.println("5. Save Current Configuration to File");
            System.out.println("6. Load Current Configuration from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    configureSystem(scanner, vendors,customers,ticketPool);
                    break;
                case 2:
                    if (vendors.isEmpty() || customers.isEmpty()) {
                        System.out.println("You must add at least one vendor and one customer. Before you can start the system.");
                    }
                    else {
                        system.start();
                    }
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
                    break;
                case 7:
                    if (system.isRunning()) {
                        system.stop();
                    }
                    System.out.println("Thank You For Using Real-Time Event Ticketing System. Good Bye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");

            }
        }
    }

    private static void displayCurrentConfiguration(Configuration config) {
        System.out.println("\n>>>>>> Current System Configuration Overview <<<<<<");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("Total Tickets                : %d\n", config.getTotalTickets());
        System.out.printf("Ticket Release Rate          : %d tickets/minute\n", config.getTicketReleaseRate());
        System.out.printf("Customer retrieval Rate      : %d tickets/minute\n", config.getCustomerRetrievalRate());
        System.out.printf("Maximum Ticket Capacity      : %d\n", config.getMaxTicketCapacity());
        System.out.println("----------------------------------------------------------------------");
    }

    private static void configureSystem(Scanner scanner, List<Vendor> vendors, List<Customer> customers, TicketPool ticketPool) {
        boolean continueOption1 = true;
        while (continueOption1) {
            System.out.println("\n-----------Manage vendors and Customers-------------------");
            System.out.println("1. Add Vendor");
            System.out.println("2. Add Customer");
            System.out.println("3. Go to main menu");
            System.out.println("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 3.");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addVendor(scanner, vendors, ticketPool);
                    break;
                case 2:
                    addCustomer(scanner, customers, ticketPool);
                    break;
                case 3:
                    continueOption1 = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1, 2 or 3.");
                    break;
            }
        }
    }

    private static void addVendor(Scanner scanner,List<Vendor> vendors, TicketPool ticketPool) {
        boolean addMoreVendors = true;
        Set<String> existingVendorIDs = new HashSet<>();
        int totalTicketsRequired = 0;
        for (Vendor vendor : vendors) {
            existingVendorIDs.add(vendor.getId());
        }

        int vendorCounter = 1;

        while (addMoreVendors) {
            System.out.println("\n>>>> Enter Vendor details <<<<");
            System.out.println("\nAdding Vendor " + vendorCounter + " : ");

            String vendorName = getValidVendorName(scanner);

            String vendorID ;
            while (true){
                System.out.print("Enter vendor ID (v001) : ");
                vendorID = scanner.next();
                if (!vendorID.matches("v\\d{3}")){
                    System.out.println("Invalid vendor ID. ID must start with 'v' followed by 3 digits.");
                    continue;
                }

                if (existingVendorIDs.contains(vendorID)){
                    System.out.println("Error: Vendor ID " + vendorID + " is already in use.Please enter a unique ID");
                }
                else {
                    existingVendorIDs.add(vendorID);
                    break;
                }
            }

            String vendorEmail = getValidEmail(scanner);

            int ticketsRequired = 0;
            System.out.print("Enter number of tickets for this vendor: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number for tickets.");
                scanner.next();
            }

            ticketsRequired = scanner.nextInt();
            totalTicketsRequired += ticketsRequired;

            System.out.print("Enter Ticket Release Rate(Per minute) : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number");
                scanner.next();
            }
            int ticketReleaseRate = scanner.nextInt();

            Vendor vendor = new Vendor(vendorName, vendorID, vendorEmail, ticketPool,ticketReleaseRate);
            vendors.add(vendor);
            System.out.println("Vendor "+ vendorName + " added successfully.");
            System.out.println("Vendor " + vendorName + "requires " + ticketsRequired + " tickets.");
            System.out.println("Total tickets required by all vendors so far: " + totalTicketsRequired);
            vendorCounter++;

            System.out.println("Do you want to add more vendors(yes/no) : ");
            String response = scanner.next();
            addMoreVendors = response.equals("yes") || response.equals("y");


        }

    }

    private static void addCustomer(Scanner scanner, List<Customer> customers, TicketPool ticketPool) {
        boolean addMoreCustomers = true;
        Set<String> existingCustomerIDs = new HashSet<>();
        int totalTicketsRequired = 0;

        for (Customer customer : customers) {
            existingCustomerIDs.add(customer.getId());

        }
        int customerCounter = 1;

        while (addMoreCustomers) {
            System.out.println("\n>>>> Enter Customer details <<<<");
            System.out.println("\nAdding Customer " + customerCounter + " : ");

            String customerName = getValidCustomerName(scanner);

            String customerID ;
            while (true){
                System.out.print("Enter vendor ID (c001) : ");
                customerID = scanner.next();
                if (!customerID.matches("c\\d{3}")){
                    System.out.println("Invalid customer ID. ID must start with 'c' followed by 3 digits.");
                    continue;
                }

                if (existingCustomerIDs.contains(customerID)){
                    System.out.println("Error: Customer ID " + customerID + " is already in use.Please enter a unique ID");
                }
                else {
                    existingCustomerIDs.add(customerID);
                    break;
                }
            }

            String customerEmail = getValidEmail(scanner);

            int ticketsRequired = 0;
            System.out.print("Enter number of tickets for this customer: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number for tickets.");
                scanner.next();
            }
            ticketsRequired = scanner.nextInt();
            totalTicketsRequired += ticketsRequired;


            System.out.print("Enter Ticket Release Rate(Per minute): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number");
                scanner.next();
            }
            int ticketRetrievalRate = scanner.nextInt();

            Customer customer = new Customer(customerName, customerID, customerEmail, ticketPool,ticketRetrievalRate);
            customers.add(customer);
            System.out.println("Customer " + customerName + " added successfully.");
            System.out.println("Customer " + customerName + "requires " + ticketsRequired + " tickets.");
            System.out.println("Total tickets required by all customers so far: " + totalTicketsRequired);
            customerCounter++;

            System.out.println("Do you want to add more customers(yes/no) : ");
            String response = scanner.next();
            addMoreCustomers = response.equals("yes") || response.equals("y");
        }
    }

    private static String getValidVendorName(Scanner scanner) {
        String vendorName;
        while (true) {
            System.out.print("Enter vendor name: ");
            vendorName = scanner.next();
            if (!vendorName.isEmpty()) {
                break;
            }
            System.out.println("Vendor name cannot be empty. Please enter a valid name.");
        }
        return vendorName;
    }


    private static String getValidCustomerName(Scanner scanner) {
        String customerName;
        while (true) {
            System.out.print("Enter customer name: ");
            customerName = scanner.next();
            if (!customerName.isEmpty()) {
                break;
            }
            System.out.println("Customer name cannot be empty. Please enter a valid name.");
        }
        return customerName;
    }

    private static String getValidEmail(Scanner scanner) {
        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.next();
            if (isValidEmail(email)) {
                break;
            }
            System.out.println("Invalid email format. Please enter a valid email.");
        }
        return email;
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    private static void saveConfiguration(Configuration config) {
        try {
            config.saveToFile();
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    private static void loadConfiguration(Configuration config) {
        try {
            config.loadFromFile();
            System.out.println("Configuration loaded successfully.");
            System.out.println("Total Tickets: " + config.getTotalTickets());
            System.out.println("Ticket release rate: " + config.getTicketReleaseRate());
            System.out.println("Customer retrieval rate: " + config.getCustomerRetrievalRate());
            System.out.println("Maximum Ticket Capacity: " + config.getMaxTicketCapacity());
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
        }
    }



}