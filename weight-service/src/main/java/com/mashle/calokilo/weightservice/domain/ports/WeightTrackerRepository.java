package com.mashle.calokilo.weightservice.domain.ports;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.WeightTracker;

import java.util.Optional;

public interface WeightTrackerRepository {

    WeightTracker save(WeightTracker weightTracker);

    Optional<WeightTracker> getById(Long userId);

    WeightEntry addWeightEntry(Long userId, WeightEntry weightEntry);
}
