package com.mashle.calokilo.weightservice.domain.ports;

import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;

public interface CreateWeightTrackerPort {

    WeightTracker createWeightTracker(Long userId, double initialWeight, double targetWeight) throws UserNotFoundException;
}
