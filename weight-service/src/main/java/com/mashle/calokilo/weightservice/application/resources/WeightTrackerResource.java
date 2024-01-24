package com.mashle.calokilo.weightservice.application.resources;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.WeightTracker;

import java.util.List;

public record WeightTrackerResource(Long userId,
                                    double initialWeight,
                                    double targetWeight,
                                    List<WeightEntry> weightHistory) {

    public WeightTrackerResource(WeightTracker weightTracker) {
        this(weightTracker.userId(), weightTracker.initialWeight(), weightTracker.targetWeight(), weightTracker.weightHistory());
    }
}
