package com.tmdb.api.domain;

import com.tmdb.api.model.user.CreateUserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Table(name = "users")
@Entity
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  public void createUser(CreateUserRequest request, BCryptPasswordEncoder passwordEncoder) {
    this.username = request.username();
    this.email = request.email();
    this.password = passwordEncoder.encode(request.password());
  }

  public boolean isLoginIncorrect(String password, BCryptPasswordEncoder passwordEncoder) {
    return !passwordEncoder.matches(password, this.password);
  }
}
