package com.oopcw.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalTickets;
    private int ticketReleaseRate;  // In milliseconds
    private int customerRetrievalRate; // In milliseconds
    private int maxTicketCapacity;
    private int numberOfVendors;
    private int numberOfCustomers;
}
