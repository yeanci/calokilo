package com.mashle.calokilo.weightservice.domain.ports;

public interface CreateWeightTrackerPort {

    void createWeightTracker(Long userId, double initialWeight, double targetWeight);
}
