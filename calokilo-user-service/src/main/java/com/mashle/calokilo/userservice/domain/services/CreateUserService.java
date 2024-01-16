package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.shared.DomainService;
import com.mashle.calokilo.userservice.domain.ports.CreateUserPort;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;

@DomainService
public class CreateUserService implements CreateUserPort {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
