package org.example;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration() {
        this.totalTickets = 100;
        this.ticketReleaseRate = 2;
        this.customerRetrievalRate = 3;
        this.maxTicketCapacity = 50;
    }

    public void setConfiguration(int totalTickets, int ticketReleaseRate,
                                 int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public void saveToFile() throws IOException {
        Properties props = new Properties();
        props.setProperty("totalTickets", String.valueOf(totalTickets));
        props.setProperty("ticketReleaseRate", String.valueOf(ticketReleaseRate));
        props.setProperty("customerRetrievalRate", String.valueOf(customerRetrievalRate));
        props.setProperty("maxTicketCapacity", String.valueOf(maxTicketCapacity));

        try(FileWriter writter = new FileWriter(CONFIG_FILE)) {
            props.store(writter, "Ticketing System Configuration");

        }
    }

    public void loadFromFile() throws IOException {
        Properties props = new Properties();
        try(FileReader reader = new FileReader(CONFIG_FILE)){
            props.load(reader);
            totalTickets = Integer.parseInt(props.getProperty("totalTickets"));
            ticketReleaseRate = Integer.parseInt(props.getProperty("ticketReleaseRate"));
            customerRetrievalRate = Integer.parseInt(props.getProperty("customerRetrievalRate"));
            maxTicketCapacity = Integer.parseInt(props.getProperty("maxTicketCapacity"));

        }
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
