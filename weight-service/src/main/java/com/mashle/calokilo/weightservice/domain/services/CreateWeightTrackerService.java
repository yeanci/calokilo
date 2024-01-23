package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.CreateWeightTrackerPort;
import com.mashle.calokilo.weightservice.domain.ports.UserRepository;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.DomainService;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;

@DomainService
public class CreateWeightTrackerService implements CreateWeightTrackerPort {

    private final WeightTrackerRepository weightTrackerRepository;
    private final UserRepository userRepository;

    public CreateWeightTrackerService(WeightTrackerRepository weightTrackerRepository, UserRepository userRepository) {
        this.weightTrackerRepository = weightTrackerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WeightTracker createWeightTracker(Long userId, double initialWeight, double targetWeight) throws UserNotFoundException {
        if(!userRepository.exists(userId))
            throw new UserNotFoundException();

        WeightTracker weightTracker = WeightTracker.builder()
                .userId(userId)
                .initialWeight(initialWeight)
                .targetWeight(targetWeight)
                .build();

        return weightTrackerRepository.save(weightTracker);
    }
}
