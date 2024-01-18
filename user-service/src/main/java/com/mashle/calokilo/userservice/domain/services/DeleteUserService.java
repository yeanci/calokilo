package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.DeleteUserPort;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.shared.DomainService;
import com.mashle.calokilo.userservice.domain.shared.UserNotFoundException;

@DomainService
public class DeleteUserService implements DeleteUserPort {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.delete(user.id());
    }
}
