package com.mashle.calokilo.userservice.application.controllers;

import com.mashle.calokilo.userservice.domain.User;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

public record CreateUserRequest(String firstName,
                                String email,
                                String password,
                                LocalDate birthDate,
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
