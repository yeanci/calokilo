package com.mashle.calokilo.weightservice.domain.shared;

public class WeightTrackerNotFoundException extends Exception {

    public WeightTrackerNotFoundException() {
        super("Weight tracker has not been found");
    }
}