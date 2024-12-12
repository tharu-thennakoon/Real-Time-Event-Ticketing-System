package com.oopcw.backend.service;

import com.oopcw.backend.entity.Configuration;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketPoolService {
    private Queue<Integer> tickets;
    private int maxCapacity;
    private AtomicInteger nextTicketId;
    private AtomicInteger totalTicketsIssued;
    private AtomicInteger totalTicketsRetrieved;
    private AtomicBoolean simulationRunning;
    private final List<String> logs;
    private ExecutorService vendorExecutor;
    private ExecutorService customerExecutor;
    private Configuration currentConfiguration;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPoolService() {
        this.maxCapacity = 100;
        this.tickets = new LinkedList<>();
        this.logs = new ArrayList<>();
        this.nextTicketId = new AtomicInteger(1);
        this.totalTicketsIssued = new AtomicInteger(0);
        this.totalTicketsRetrieved = new AtomicInteger(0);
        this.simulationRunning = new AtomicBoolean(false);
    }

    public void initializeSimulation(Configuration config) {
        lock.lock();
        try {
            if (simulationRunning.get()) {
                stopSimulation();
            }
            
            this.currentConfiguration = config;
            this.maxCapacity = config.getMaxTicketCapacity();
            this.tickets.clear();
            this.nextTicketId.set(1);
            this.totalTicketsIssued.set(0);
            this.totalTicketsRetrieved.set(0);
            this.logs.clear();
            
            log("Simulation initialized with configuration: " + config);
        } finally {
            lock.unlock();
        }
    }

    public String startSimulation() {
        if (simulationRunning.get()) {
            return "Simulation is already running.";
        }
        
        if (currentConfiguration == null) {
            return "No configuration set. Please set a configuration first.";
        }

        simulationRunning.set(true);
        
        // Create thread pools
        vendorExecutor = Executors.newFixedThreadPool(currentConfiguration.getNumberOfVendors());
        customerExecutor = Executors.newFixedThreadPool(currentConfiguration.getNumberOfCustomers());

        // Start Vendor Threads
        for (int i = 0; i < currentConfiguration.getNumberOfVendors(); i++) {
            final int vendorId = i + 1;
            vendorExecutor.submit(() -> runVendorSimulation(vendorId));
        }

        // Start Customer Threads
        for (int i = 0; i < currentConfiguration.getNumberOfCustomers(); i++) {
            final int customerId = i + 1;
            customerExecutor.submit(() -> runCustomerSimulation(customerId));
        }

        log("Simulation started with " + 
            currentConfiguration.getNumberOfVendors() + " vendors and " + 
            currentConfiguration.getNumberOfCustomers() + " customers");
            
        return "Simulation started successfully.";
    }

    private void runVendorSimulation(int vendorId) {
        while (simulationRunning.get() && totalTicketsIssued.get() < currentConfiguration.getTotalTickets()) {
            lock.lock();
            try {
                if (tickets.size() < maxCapacity && totalTicketsIssued.get() < currentConfiguration.getTotalTickets()) {
                    int ticketId = nextTicketId.getAndIncrement();
                    tickets.add(ticketId);
                    totalTicketsIssued.incrementAndGet();
                    log("Vendor " + vendorId + " issued ticket: " + ticketId);
                }
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(currentConfiguration.getTicketReleaseRate());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void runCustomerSimulation(int customerId) {
        while (simulationRunning.get() && totalTicketsRetrieved.get() < currentConfiguration.getTotalTickets()) {
            Integer ticketId = removeTicket();
            if (ticketId != null) {
                totalTicketsRetrieved.incrementAndGet();
                log("Customer " + customerId + " retrieved ticket: " + ticketId);
            }

            if (ticketId != null) {
                try {
                    Thread.sleep(currentConfiguration.getCustomerRetrievalRate());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public Integer removeTicket() {
        lock.lock();
        try {
            if (!tickets.isEmpty()) {
                return tickets.poll();  // Remove and return the ticket from the queue
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void addTickets(int numTickets) {
        lock.lock();
        try {
            for (int i = 0; i < numTickets; i++) {
                if (totalTicketsIssued.get() < currentConfiguration.getTotalTickets()) {
                    int ticketId = nextTicketId.getAndIncrement();
                    tickets.add(ticketId);
                    totalTicketsIssued.incrementAndGet();
                    log("Ticket added to pool: " + ticketId);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public String stopSimulation() {
        simulationRunning.set(false);
        
        if (vendorExecutor != null) {
            vendorExecutor.shutdownNow();
        }
        if (customerExecutor != null) {
            customerExecutor.shutdownNow();
        }

        log("Simulation stopped.");
        return "Simulation stopped successfully.";
    }

    public String getAnalytics() {
        return String.format(
            "Total Tickets Issued: %d\n" +
            "Total Tickets Retrieved: %d\n" +
            "Current Pool Size: %d\n" +
            "Max Pool Capacity: %d\n" +
            "Simulation Status: %s",
            totalTicketsIssued.get(),
            totalTicketsRetrieved.get(),
            tickets.size(),
            maxCapacity,
            simulationRunning.get() ? "Running" : "Stopped"
        );
    }

    private void log(String message) {
        String timestamp = String.format("[%tF %<tT]", System.currentTimeMillis());
        String logMessage = timestamp + " " + message;
        logs.add(logMessage);
        System.out.println(logMessage);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public boolean isSimulationRunning() {
        return simulationRunning.get();
    }

    public int getTotalTicketsIssued() {
        return totalTicketsIssued.get();
    }

    public int getTotalTicketsRetrieved() {
        return totalTicketsRetrieved.get();
    }
}
