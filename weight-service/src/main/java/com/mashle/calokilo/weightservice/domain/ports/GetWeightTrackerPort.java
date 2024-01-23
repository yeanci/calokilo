package com.mashle.calokilo.weightservice.domain.ports;

import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;

public interface GetWeightTrackerPort {

    WeightTracker getWeightTracker(Long userId) throws UserNotFoundException;
}
