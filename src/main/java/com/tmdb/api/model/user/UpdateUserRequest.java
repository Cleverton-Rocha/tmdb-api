package com.tmdb.api.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @Size(min = 3, max = 55, message = "Username must have between 3 and 100 characters.")
    String username,

    @Email(message = "Write a valid email.")
    String email,

    @Size(min = 8, max = 100, message = "Password must have at least 8 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
        message = "Password must have at least one uppercase letter, one lowercase letter and "
            + "one number.")
    String password
) {

}
