package com.tmdb.api.model.auth;

public record LoginResponse(String accessToken, String username, Long expiresIn) {
}
