package com.mashle.calokilo.weightservice.domain.ports;

import com.mashle.calokilo.weightservice.domain.WeightTracker;

public interface CreateWeightTrackerPort {

    WeightTracker createWeightTracker(Long userId, double initialWeight, double targetWeight);
}
