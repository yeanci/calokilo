package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.GetWeightTrackerPort;
import com.mashle.calokilo.weightservice.domain.ports.UserRepository;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.DomainService;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;
import com.mashle.calokilo.weightservice.domain.shared.WeightTrackerNotFoundException;

@DomainService
public class GetWeightTrackerService implements GetWeightTrackerPort {

    private final WeightTrackerRepository weightTrackerRepository;
    private final UserRepository userRepository;

    public GetWeightTrackerService(WeightTrackerRepository weightTrackerRepository, UserRepository userRepository) {
        this.weightTrackerRepository = weightTrackerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WeightTracker getWeightTracker(Long userId) throws UserNotFoundException, WeightTrackerNotFoundException {
        if(!userRepository.exists(userId))
            throw new UserNotFoundException();

        return weightTrackerRepository.getById(userId).orElseThrow(WeightTrackerNotFoundException::new);
    }
}
