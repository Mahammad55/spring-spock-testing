package com.example.spocktestimplementation.eception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found by id = %s".formatted(id));
    }

    public UserNotFoundException() {
        super("User not found");
    }
}
