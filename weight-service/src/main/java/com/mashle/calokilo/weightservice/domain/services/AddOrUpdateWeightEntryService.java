package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.AddOrUpdateWeightEntryPort;
import com.mashle.calokilo.weightservice.domain.ports.UserRepository;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.*;

@DomainService
public class AddOrUpdateWeightEntryService implements AddOrUpdateWeightEntryPort {

    private final WeightTrackerRepository weightTrackerRepository;
    private final UserRepository userRepository;

    public AddOrUpdateWeightEntryService(WeightTrackerRepository weightTrackerRepository, UserRepository userRepository) {
        this.weightTrackerRepository = weightTrackerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WeightEntry addOrUpdateWeightEntry(Long userId, WeightEntry weightEntry) throws NotFoundException {
        if(!userRepository.exists(userId))
            throw new UserNotFoundException();

        WeightTracker weightTracker = weightTrackerRepository.getById(userId).orElseThrow(WeightTrackerNotFoundException::new);

        return weightTrackerRepository.save(weightTracker.addOrUpdateWeightEntry(weightEntry)).weightHistory().stream()
                .filter(entry -> entry.entryDate().isEqual(weightEntry.entryDate()))
                .findFirst()
                .orElseThrow(WeightEntryNotFoundException::new);
    }
}
