package com.mashle.calokilo.userservice.domain.ports;

import com.mashle.calokilo.userservice.domain.User;

import java.util.Optional;

public interface GetUserByIdPort {

    Optional<User> getUserById(Long id);
}
