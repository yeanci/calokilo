package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.ports.DeleteUserPort;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.shared.DomainService;

@DomainService
public class DeleteUserService implements DeleteUserPort {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
