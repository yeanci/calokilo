package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.GetWeightTrackerPort;
import com.mashle.calokilo.weightservice.domain.ports.UserRepository;

public class GetWeightTrackerService implements GetWeightTrackerPort {

    private final UserRepository userRepository;

    public GetWeightTrackerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public WeightTracker getWeightTracker(Long userId) {
        return null;
    }
}
