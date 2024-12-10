package com.oopcw.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
    private Long id;
    private String name;
    private int ticketReleaseRate; // In milliseconds
}