package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.Upload;
import com.example.jubileebackendeindopdracht.model.User;
import jakarta.persistence.Lob;
import jakarta.validation.Valid;
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
    private String email;

    @Lob
    private byte[] profileUpload;

    @Past
    private LocalDate birthDate;

    @Valid
    private Account account;
    private User user;
    private Upload upload;

}
