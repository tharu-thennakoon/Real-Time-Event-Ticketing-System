package com.oopcw.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Pattern(regexp = "^(Consumer|Producer)$", message = "Role must be either 'Consumer' or 'Producer'")
    private String role; // Restrict to "Consumer" or "Producer"
}
