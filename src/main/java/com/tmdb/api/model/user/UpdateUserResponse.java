package com.tmdb.api.model.user;

import com.tmdb.api.domain.User;

public record UpdateUserResponse(Long id, String username, String email, String password) {

  public UpdateUserResponse(User user) {
    this(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
  }
}
