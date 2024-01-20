package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.CreateWeightTrackerPort;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.DomainService;

@DomainService
public class CreateWeightTrackerService implements CreateWeightTrackerPort {

    private final WeightTrackerRepository weightTrackerRepository;

    public CreateWeightTrackerService(WeightTrackerRepository weightTrackerRepository) {
        this.weightTrackerRepository = weightTrackerRepository;
    }

    @Override
    public void createWeightTracker(Long userId, double initialWeight, double targetWeight) {

    }
}
