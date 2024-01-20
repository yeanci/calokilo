package com.mashle.calokilo.weightservice.domain.shared;

public class NotValidWeightTrackerException extends IllegalArgumentException {

    public NotValidWeightTrackerException(String message) {
        super(message);
    }
}
