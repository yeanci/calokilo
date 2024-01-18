package com.mashle.calokilo.userservice.application.resources;

import com.mashle.calokilo.userservice.domain.User;

import java.time.LocalDate;

public record UserResource(Long id,
                           String firstName,
                           String email,
                           LocalDate birthDate,
                           int height) {

    public UserResource(User user) {
        this(user.id(), user.firstName(), user.email(), user.birthDate(), user.height());
    }
}
