package com.mashle.calokilo.weightservice.domain.shared;

public class WeightEntryNotFoundException extends NotFoundException {

    public WeightEntryNotFoundException() {
        super("Added weight entry has not been found");
    }
}