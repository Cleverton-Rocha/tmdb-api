package com.tmdb.api.service;

import com.tmdb.api.domain.User;
import com.tmdb.api.model.user.CreateUserRequest;
import com.tmdb.api.model.user.CreateUserResponse;
import com.tmdb.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public CreateUserResponse createUser(CreateUserRequest request) {
    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new RuntimeException("Email already in use");
    }

    if (userRepository.findByUsername(request.username()).isPresent()) {
      throw new RuntimeException("Username already in use");
    }

    User newUser = new User();
    newUser.createUser(request, passwordEncoder);

    userRepository.save(newUser);

    return new CreateUserResponse(newUser);
  }
}
