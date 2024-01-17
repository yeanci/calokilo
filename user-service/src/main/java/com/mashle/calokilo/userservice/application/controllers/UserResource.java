package com.mashle.calokilo.userservice.application.controllers;

import com.mashle.calokilo.userservice.domain.User;

import java.time.LocalDate;

public record UserResource(Long id,
                          String firstName,
                          String email,
                          LocalDate birthDate,
                          int height) {

    public static UserResource from(User user) {
        return new UserResource(user.id(), user.firstName(), user.email(), user.birthDate(), user.height());
    }
}