package com.tmdb.api.service;

import com.tmdb.api.model.auth.LoginRequest;
import com.tmdb.api.model.auth.LoginResponse;
import com.tmdb.api.repository.UserRepository;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final JwtEncoder jwtEncoder;
  private final BCryptPasswordEncoder passwordEncoder;

  public AuthService(UserRepository userRepository, JwtEncoder jwtEncoder,
      BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.jwtEncoder = jwtEncoder;
    this.passwordEncoder = passwordEncoder;
  }

  public LoginResponse login(LoginRequest request) {

    var user = userRepository.findByUsername(request.username());

    if (user.isEmpty() || user.get().isLoginIncorrect(request.password(), passwordEncoder)) {
      throw new BadCredentialsException("Invalid username or password.");
    }

    var now = Instant.now().atZone(ZoneId.systemDefault());
    var expiresIn = 7200L;

    var claims = JwtClaimsSet.builder().issuer("ultimate-bank").subject(request.username())
        .issuedAt(now.toInstant()).expiresAt(now.plusSeconds(expiresIn).toInstant()).build();

    var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    return new LoginResponse(jwtValue, request.username(), expiresIn);
  }
}
