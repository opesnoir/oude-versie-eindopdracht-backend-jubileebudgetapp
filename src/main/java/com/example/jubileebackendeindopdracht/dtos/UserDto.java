package com.example.jubileebackendeindopdracht.dtos;

import com.example.jubileebackendeindopdracht.models.Authority;
import com.example.jubileebackendeindopdracht.models.Upload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class UserDto {

    @NotBlank
    @Size(min = 3, max = 50, message = " at least 3 to 50 characters long")
    private String username;

    @Size(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "The password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    private String password;

    public Boolean enabled;
    public String apikey;

    @Email
    private String email;

    public Set<Authority> authorities;
    private Long accountId;

}
