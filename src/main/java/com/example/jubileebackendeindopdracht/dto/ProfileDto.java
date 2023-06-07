package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class ProfileDto {

    private Long id;
    private String username;
    private String password;
    private String emailAddress;
    private LocalDate birthDate;
    private User user;

}
