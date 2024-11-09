package org.example;

public class Configuration {
    private int totalTicket;
    private int ticketReleaseRate;
    private int customerReleaseRate;
    private int maxTicketCapacity;

    public Configuration (int totalTicket, int ticketReleaseRate, int customerReleaseRate, int maxTicketCapacity) {
        this.totalTicket = totalTicket;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerReleaseRate = customerReleaseRate;
        this.maxTicketCapacity = maxTicketCapacity;

    }
}
