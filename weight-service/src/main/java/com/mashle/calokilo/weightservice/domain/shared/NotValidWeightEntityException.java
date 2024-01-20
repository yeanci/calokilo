package com.mashle.calokilo.weightservice.domain.shared;

public class NotValidWeightEntityException extends IllegalArgumentException {

    public NotValidWeightEntityException(String message) {
        super(message);
    }
}
