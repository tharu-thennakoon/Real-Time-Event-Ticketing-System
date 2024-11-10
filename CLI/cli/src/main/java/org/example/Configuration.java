package org.example;

public class Configuration {
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final int customerReleaseRate;
    private final int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerReleaseRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerReleaseRate = customerReleaseRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerReleaseRate() {
        return customerReleaseRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

}
