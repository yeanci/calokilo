package com.mashle.calokilo.weightservice.domain.ports;

import com.mashle.calokilo.weightservice.domain.WeightTracker;

public interface GetWeightTrackerPort {

    WeightTracker getWeightTracker(Long userId);
}
