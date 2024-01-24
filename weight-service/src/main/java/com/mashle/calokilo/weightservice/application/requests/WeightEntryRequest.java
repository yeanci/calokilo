package com.mashle.calokilo.weightservice.application.requests;

import com.mashle.calokilo.weightservice.domain.WeightEntry;

import java.time.LocalDate;

public record WeightEntryRequest(double weight,
                                 LocalDate entryDate) {

    public WeightEntry toWeightEntry() {
        return WeightEntry.builder()
                .weight(weight)
                .entryDate(entryDate)
                .build();
    }
}
