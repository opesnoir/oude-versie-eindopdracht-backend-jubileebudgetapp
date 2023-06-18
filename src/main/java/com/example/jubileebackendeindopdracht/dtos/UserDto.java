package com.example.jubileebackendeindopdracht.dtos;

import com.example.jubileebackendeindopdracht.models.Authority;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class UserDto {

    @NotBlank
    @Size(min = 3, max = 50, message = " at least 3 to 50 characters long")
    public String username;

    @Size(min = 8, max = 50)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "The password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
    public String password;

    public Boolean enabled;
    public String apikey;

    @Email
    public String email;

    @JsonSerialize
    public Set<Authority> authorities;

    private Long accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(username, userDto.username) && Objects.equals(password, userDto.password) && Objects.equals(enabled, userDto.enabled) && Objects.equals(apikey, userDto.apikey) && Objects.equals(email, userDto.email) && Objects.equals(authorities, userDto.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled, apikey, email, authorities);
    }


}
