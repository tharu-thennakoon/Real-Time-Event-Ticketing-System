package org.example;
import java.util.Scanner;

public class Main {
    private static TicketingSystem system;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("        Welcome to the Real-Time Event Ticketing System     ");

        int totalTicket = 10;
        int ticketReleaseRate = 2;
        int customerReleaseRate = 3;
        int maxTicketCapacity = 20;

        Configuration config = new Configuration(totalTicket, ticketReleaseRate, customerReleaseRate, maxTicketCapacity);
        system = new TicketingSystem(config);

        boolean running = true;
        while (running){
            displayMenu();
            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    startSystem();
                    break;
                case 2:
                    stopSystem();
                    break;
                case 3:
                    displayStatus();
                    break;
                case 4:
                    running = false;
                    if (system.isRunning()){
                        stopSystem();
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using Real-Time Event Ticketing System! Goodbye!");

    }

    private static void displayMenu() {
        System.out.println("System Menu : ");
        System.out.println("1. Start System");
        System.out.println("2. Stop System");
        System.out.println("3. Display Status");
        System.out.println("4. Exit");
        System.out.println("Enter your choice : ");
    }

    private static int getValidIntInput() {
        while (true) {
            try{
                int value = scanner.nextInt();
                if (value <= 0){
                    System.out.println("Invalid input. Please enter a positive number.");
                    continue;
                }
                return value;
            }
            catch (Exception e){
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    private static void startSystem() {
        if(!system.isRunning()){
            system.start();
            System.out.println("System started successfully.");
        }
        else {
            System.out.println("System is already running.");
        }
    }

    private static void stopSystem() {
        if(system.isRunning()){
            system.stop();
            System.out.println("System stopped successfully.");
        }
        else {
            System.out.println("System is already stopped.");
        }
    }

    private static void displayStatus() {
        System.out.println("System Status : ");
        System.out.println("System running : " + system.isRunning());
        System.out.println("Available Tickets : " + system.getAvailableTickets());
    }
}