package com.mashle.calokilo.userservice.domain.ports;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.shared.NotValidUserException;

public interface CreateUserPort {

    User createUser(User user) throws NotValidUserException;
}
