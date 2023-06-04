package com.example.jubileebackendeindopdracht.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// constructors, one empty and one with all variables
@NoArgsConstructor
@AllArgsConstructor

// getters and setters for all variable declarations
@Getter
@Setter

@Entity
@Table(name = "profiles")
public class Profile {
    //variables declaration
    // Primary key (id) of the entity, automatically generated using identity column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "birth_date")
    private LocalDate birthDate;
}
