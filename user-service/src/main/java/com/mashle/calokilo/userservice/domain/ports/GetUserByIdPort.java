package com.mashle.calokilo.userservice.domain.ports;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.shared.UserNotFoundException;

import java.util.Optional;

public interface GetUserByIdPort {

    User getUserById(Long id) throws UserNotFoundException;
}
