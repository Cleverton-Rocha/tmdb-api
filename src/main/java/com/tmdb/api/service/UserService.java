package com.tmdb.api.service;

import com.tmdb.api.domain.User;
import com.tmdb.api.exception.IsNotUniqueException;
import com.tmdb.api.exception.NotFoundException;
import com.tmdb.api.model.user.CreateUserRequest;
import com.tmdb.api.model.user.CreateUserResponse;
import com.tmdb.api.model.user.UpdateUserRequest;
import com.tmdb.api.model.user.UpdateUserResponse;
import com.tmdb.api.model.user.UserResponse;
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
      throw new NotFoundException("Email already in use");
    }

    if (userRepository.findByUsername(request.username()).isPresent()) {
      throw new NotFoundException("Username already in use");
    }

    User newUser = new User();
    newUser.createUser(request, passwordEncoder);

    userRepository.save(newUser);

    return new CreateUserResponse(newUser);
  }

  @Transactional
  public UserResponse getUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not found"));

    return new UserResponse(user);
  }

  @Transactional
  public UpdateUserResponse updateUser(Long id, UpdateUserRequest request) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not found."));

    if (request.username() != null) {
      if (!request.username().equals(user.getUsername()) && userRepository.findByUsername(
          request.username()).isPresent()) {
        throw new IsNotUniqueException("Email already exists.");
      }
      user.setUsername(request.username());
    }

    if (request.email() != null) {
      if (!request.email().equals(user.getEmail()) && userRepository.findByEmail(request.email())
          .isPresent()) {
        throw new IsNotUniqueException("Email already exists.");
      }
      user.setEmail(request.email());
    }

    if (request.password() != null) {
      user.setPassword(passwordEncoder.encode(request.password()));
    }

    userRepository.save(user);
    return new UpdateUserResponse(user);
  }

  @Transactional
  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("User not found."));

    userRepository.delete(user);
  }
}
