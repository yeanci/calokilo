package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserCreatedMessenger;
import com.mashle.calokilo.userservice.domain.shared.DomainService;
import com.mashle.calokilo.userservice.domain.ports.CreateUserPort;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.shared.NotValidUserException;

@DomainService
public class CreateUserService implements CreateUserPort {

    private final UserRepository userRepository;
    private final UserCreatedMessenger userCreatedMessenger;

    public CreateUserService(UserRepository userRepository, UserCreatedMessenger userCreatedMessenger) {
        this.userRepository = userRepository;
        this.userCreatedMessenger = userCreatedMessenger;
    }

    @Override
    public User createUser(User user, double initialWeight, double targetWeight) throws NotValidUserException {
        validateInitialWeight(initialWeight);
        validateTargetWeight(targetWeight);

        User createdUser = userRepository.save(user);

        userCreatedMessenger.notifyUserCreated(createdUser.id(), initialWeight, targetWeight);

        return createdUser;
    }

    private void validateInitialWeight(double weight) {
        if (weight < 0) {
            throw new NotValidUserException("Invalid initial weight");
        }
    }

    private void validateTargetWeight(double weight) {
        if (weight < 0) {
            throw new NotValidUserException("Invalid target weight");
        }
    }
}
