package com.mashle.calokilo.weightservice.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record WeightTracker(Long userId,
                            double initialWeight,
                            double targetWeight,
                            List<WeightEntry> weightHistory) {
}
