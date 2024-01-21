package com.mashle.calokilo.weightservice.domain.shared;

public class NotValidWeightEntryException extends IllegalArgumentException {

    public NotValidWeightEntryException(String message) {
        super(message);
    }
}
