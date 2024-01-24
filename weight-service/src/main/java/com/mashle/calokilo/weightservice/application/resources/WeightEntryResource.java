package com.mashle.calokilo.weightservice.application.resources;

import com.mashle.calokilo.weightservice.domain.WeightEntry;

import java.time.LocalDate;

public record WeightEntryResource(double weight,
                                  LocalDate entryDate) {

    public WeightEntryResource(WeightEntry weightEntry) {
        this(weightEntry.weight(), weightEntry.entryDate());
    }
}
