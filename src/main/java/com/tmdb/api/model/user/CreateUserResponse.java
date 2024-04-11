package com.tmdb.api.model.user;

import com.tmdb.api.domain.User;

public record CreateUserResponse(Long id, String username, String email, String password) {

  public CreateUserResponse(User user) {
    this(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
  }
}
