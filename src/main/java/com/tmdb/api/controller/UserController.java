package com.tmdb.api.controller;

import com.tmdb.api.model.user.CreateUserRequest;
import com.tmdb.api.model.user.CreateUserResponse;
import com.tmdb.api.model.user.UpdateUserRequest;
import com.tmdb.api.model.user.UpdateUserResponse;
import com.tmdb.api.model.user.UserResponse;
import com.tmdb.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<CreateUserResponse> createUser(
      @RequestBody @Valid CreateUserRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long id,
      @RequestBody @Valid UpdateUserRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
