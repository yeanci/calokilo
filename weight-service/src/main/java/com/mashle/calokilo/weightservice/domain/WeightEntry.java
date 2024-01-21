package com.mashle.calokilo.weightservice.domain;

import com.mashle.calokilo.weightservice.domain.shared.NotValidWeightEntryException;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WeightEntry(Long id,
                          double weight,
                          LocalDate entryDate) {

    public WeightEntry {
        validateWeight(weight);
        validateEntryDate(entryDate);
    }

    private void validateWeight(double weight) {
        if (weight < 0) {
            throw new NotValidWeightEntryException("Invalid weight");
        }
    }

    private void validateEntryDate(LocalDate entryDate) {
        if (entryDate == null || entryDate.isAfter(LocalDate.now())) {
            throw new NotValidWeightEntryException("Invalid entry date");
        }
    }
}
