package com.mashle.calokilo.weightservice.domain;

import com.mashle.calokilo.weightservice.domain.shared.NotValidWeightTrackerException;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Builder
public record WeightTracker(Long userId,
                            double initialWeight,
                            double targetWeight,
                            List<WeightEntry> weightHistory) {

    public WeightTracker {
        validateInitialWeight(initialWeight);
        validateTargetWeight(targetWeight);

        if(weightHistory == null) {
            weightHistory = new ArrayList<>();
            weightHistory.add(WeightEntry.builder().weight(initialWeight).entryDate(LocalDate.now()).build());
        }
    }

    private void validateInitialWeight(double weight) {
        if (weight < 0) {
            throw new NotValidWeightTrackerException("Invalid weight");
        }
    }

    private void validateTargetWeight(double weight) {
        if (weight < 0) {
            throw new NotValidWeightTrackerException("Invalid weight");
        }
    }

    public WeightTracker addOrUpdateWeightEntry(WeightEntry weightEntry) {
        Map<LocalDate, WeightEntry> dateIndex = weightHistory.stream()
                .collect(Collectors.toMap(WeightEntry::entryDate, Function.identity()));

        dateIndex.put(weightEntry.entryDate(), weightEntry);

        return new WeightTracker(this.userId, this.initialWeight, this.targetWeight, new ArrayList<>(dateIndex.values()));
    }
}
