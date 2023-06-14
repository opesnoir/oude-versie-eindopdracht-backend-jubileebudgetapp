package com.example.jubileebackendeindopdracht.dto;

import com.example.jubileebackendeindopdracht.model.Account;
import com.example.jubileebackendeindopdracht.model.Profile;
import com.example.jubileebackendeindopdracht.model.Upload;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class UserDto {

    private Long id;
    @NotBlank
    @Size(min = 3, max = 50, message = " at least 3 to 50 characters long")
    private String username;
    @Size(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "The password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;
    @Email
    private String email;

    @Valid
    private Account account;
    private Long accountId;

    private Profile profile;
    private Long profileId;

    private Upload upload;
    private Long uploadId;

}
