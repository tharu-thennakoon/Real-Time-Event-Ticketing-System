package org.example;

import java.lang.module.Configuration;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static TicketingSystem;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("          Welcome to Real-Time Event Ticketing System        ");

        int totalTicket = 10;
        int ticketReleaseRate = 2;
        int customerReleaseRate = 3;
        int maxTicketCapacity = 20;

        Configuration config = new Configuration(totalTicket,ticketReleaseRate,customerReleaseRate,maxTicketCapacity);
        system = new TicketingSystem(config);

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getValidInput();

            switch (choice){
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
                    System.out.println("Invalid choice. Please try again");
            }

        }

        scanner.close();
        System.out.println("Thank you for using Real-Time Event Ticketing System!");
    }

    private static void displayMenu(){
        System.out.println("\nEvent Ticketing System Menu:");
        System.out.println("1. Start Ticketing System");
        System.out.println("2. Stop Ticketing System");
        System.out.println("3. Display Status");
        System.out.println("4. Exit");
        System.out.println("Enter your choice : ");
    }

    private static void getValidInput(){
        while(true){
            try {
                int value = scanner.nextInt();
                if (value <= 0){
                    System.out.println("Invalid input. Please try again");
                    continue;
                }

            }
            catch (Exception e){
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    private static void startSystem(){
        if (!system.isRunning()) {
            System.out.println("System is already running.");
        } else {
            system.start();
            System.out.println("System started successfully.");
        }
    }

    private static void stopSystem(){
        if (system.isRunning()){
            system.stop();
            System.out.println("System stopped successfully.");
        }
        else {
            System.out.println("System is not running!");
        }
    }

    private static void displayStatus(){
        System.out.println("\nEvent Ticketing System Status:");
        System.out.println("System running : " + system.isRunning());
        System.out.println("Available tickets : " + system.getAvailableTickets());
    }

}