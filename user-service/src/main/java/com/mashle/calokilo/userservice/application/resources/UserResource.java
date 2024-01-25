package com.mashle.calokilo.userservice.application.resources;

import com.mashle.calokilo.userservice.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "User details")
public record UserResource(
        @Schema(description = "User Id", example = "1")
        Long id,
        @Schema(description = "User's first name", example = "John")
        String firstName,
        @Schema(description = "User's email", example = "john@example.com")
        String email,
        @Schema(description = "User's birth date", example = "1984-12-22")
        LocalDate birthDate,
        @Schema(description = "User's birth date", example = "180")
        int height) {

    public UserResource(User user) {
        this(user.id(), user.firstName(), user.email(), user.birthDate(), user.height());
    }
}
