package com.mashle.calokilo.userservice.domain.ports;

import com.mashle.calokilo.userservice.domain.User;

import java.util.List;

public interface GetAllUsersPort {

    List<User> getAllUsers();
}
