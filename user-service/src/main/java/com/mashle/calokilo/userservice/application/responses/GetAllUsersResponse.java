package com.mashle.calokilo.userservice.application.responses;

import com.mashle.calokilo.userservice.domain.User;

import java.util.List;

public record GetAllUsersResponse(List<User> users) {

    public static GetAllUsersResponse from(List<User> allUsers) {
        return new GetAllUsersResponse(allUsers);
    }
}
