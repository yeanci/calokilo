package com.mashle.calokilo.weightservice.domain.ports;

import com.mashle.calokilo.weightservice.domain.WeightTracker;

public interface WeightTrackerRepository {

    WeightTracker save(WeightTracker weightTracker);
}
