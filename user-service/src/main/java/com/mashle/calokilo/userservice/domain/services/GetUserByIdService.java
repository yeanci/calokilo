package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.GetUserByIdPort;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.shared.DomainService;

import java.util.Optional;

@DomainService
public class GetUserByIdService implements GetUserByIdPort{

    private final UserRepository userRepository;

    public GetUserByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
