package com.mashle.calokilo.userservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
public record User(Long id,
                   String firstName,
                   String email,
                   String password,
                   LocalDate birthDate,
                   int height) {

    public User {
        validateFirstName(firstName);
        validateEmail(email);
        validatePassword(password);
        validateBirthDate(birthDate);
        validateHeight(height);
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.length() < 2 || firstName.length() > 20) {
            throw new IllegalArgumentException("Invalid first name");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 10) {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid birth date");
        }
    }

    private void validateHeight(int height) {
        if (height <= 0 || height > 300) {
            throw new IllegalArgumentException("Invalid height");
        }
    }
}
