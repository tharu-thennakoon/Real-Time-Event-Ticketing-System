package com.oopcw.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Auto Genarate Id
    private Long id;

    private String name; // Vendor name
    private int ticketReleaseRate; // Time (in seconds) between releasing tickets
}
