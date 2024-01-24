package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.ports.AddWeightEntryPort;
import com.mashle.calokilo.weightservice.domain.ports.UserRepository;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.DomainService;

@DomainService
public class AddWeightEntryService implements AddWeightEntryPort {

    private final WeightTrackerRepository weightTrackerRepository;
    private final UserRepository userRepository;

    public AddWeightEntryService(WeightTrackerRepository weightTrackerRepository, UserRepository userRepository) {
        this.weightTrackerRepository = weightTrackerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WeightEntry addWeightEntry(Long userId, WeightEntry weightEntry) {
        return null;
    }
}
