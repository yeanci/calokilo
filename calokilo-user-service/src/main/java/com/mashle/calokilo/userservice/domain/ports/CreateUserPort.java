package com.mashle.calokilo.userservice.domain.ports;

import com.mashle.calokilo.userservice.domain.User;

public interface CreateUserPort {

    User createUser(User user);
}
