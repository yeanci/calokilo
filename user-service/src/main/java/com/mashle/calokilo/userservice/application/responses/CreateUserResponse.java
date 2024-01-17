package com.mashle.calokilo.userservice.application.responses;

import com.mashle.calokilo.userservice.domain.User;

import java.time.LocalDate;

public record CreateUserResponse(Long id,
                                 String firstName,
                                 String email,
                                 LocalDate birthDate,
                                 int height) {

    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(user.id(), user.firstName(), user.email(), user.birthDate(), user.height());
    }
}