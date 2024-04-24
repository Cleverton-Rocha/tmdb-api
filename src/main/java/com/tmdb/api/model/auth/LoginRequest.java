package com.tmdb.api.model.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotNull
    @Size(min = 3, max = 55, message = "Username must have at least 3 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must contain only letters and numbers.")
    String username,

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 100, message = "Password must have at least 8 characters.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
        message = "Password must have at least one uppercase letter, one lowercase letter and "
            + "one number.")
    String password) {

}