package com.tmdb.api.exception;

public class IsNotUniqueException extends RuntimeException {

  public IsNotUniqueException(String message) {
    super(message);
  }
}
