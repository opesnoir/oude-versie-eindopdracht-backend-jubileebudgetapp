package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.User;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
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

    @Email
    private String emailAddress;

    @Past
    private LocalDate birthDate;

    @Lob
    private byte[] profileImage;

    private User user;

}
