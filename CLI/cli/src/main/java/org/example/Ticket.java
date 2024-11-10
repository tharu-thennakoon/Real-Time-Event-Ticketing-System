package org.example;

public class Ticket {
    private final int id;
    private final int vendorId;

    public Ticket(int id, int vendorId) {
        this.id = id;
        this.vendorId = vendorId;
    }

    public int getId() {
        return id;
    }

    public int getVendorId() {
        return vendorId;
    }
}
