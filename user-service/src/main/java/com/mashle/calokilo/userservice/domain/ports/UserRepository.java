package com.mashle.calokilo.userservice.domain.ports;

import com.mashle.calokilo.userservice.domain.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    List<User> findAll();
}
