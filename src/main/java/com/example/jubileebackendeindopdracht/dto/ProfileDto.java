package com.example.jubileebackendeindopdracht.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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

public class ProfileDto {

    //variables declaration
    private Long id;

    @Size(min = 3, max = 50)
    private String username;

    @Size(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "The password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;

    @Email
    private String emailAddress;

    @Past
    private LocalDate birthDate;

}
