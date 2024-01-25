package com.mashle.calokilo.userservice.application.requests;

import com.mashle.calokilo.userservice.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

public record CreateUserRequest(
        @Schema(description = "User's first name", example = "John")
        String firstName,
        @Schema(description = "User's email", example = "john@example.com")
        String email,
        @Schema(description = "User's password", example = "johnVerySecretP@ssword")
        String password,
        @Schema(description = "User's birth date", example = "1984-12-22")
        LocalDate birthDate,
        @Schema(description = "User's birth date", example = "180")
        int height) {

    public User toUser() {
        return User.builder()
                .firstName(firstName)
                .email(email)
                .password(password)
                .birthDate(birthDate)
                .height(height)
                .build();
    }
}
