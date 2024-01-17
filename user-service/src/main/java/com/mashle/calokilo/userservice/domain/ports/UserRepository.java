package com.mashle.calokilo.userservice.domain.ports;

import com.mashle.calokilo.userservice.domain.User;

public interface UserRepository {

    User save(User user);
}
