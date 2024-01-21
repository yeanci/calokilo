package com.mashle.calokilo.weightservice.domain;

import com.mashle.calokilo.weightservice.domain.shared.NotValidWeightTrackerException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeightTrackerTest {

    @Test
    void createWeightTracker_whenValidData_thenCreate() {
        assertDoesNotThrow(() ->
            WeightTracker.builder()
                .userId(1L)
                .initialWeight(83.7)
                .targetWeight(75.6)
                .build()
        );
    }

    @Test
    void createWeightTracker_whenValidData_thenHistoryIsInitialized() {
        double initialWeight = 83.7;

        WeightTracker weightTracker = WeightTracker.builder()
            .userId(1L)
            .initialWeight(initialWeight)
            .targetWeight(75.6)
            .build();

        assertThat(weightTracker.weightHistory()).isNotEmpty();
        assertThat(weightTracker.weightHistory().getFirst().weight()).isEqualTo(initialWeight);
        assertThat(weightTracker.weightHistory().getFirst().entryDate()).isToday();
    }

    @Test
    void createWeightTracker_whenNegativeInitialWeight_thenThrowException() {
        assertThrows(NotValidWeightTrackerException.class, () ->
            WeightTracker.builder()
                .userId(1L)
                .initialWeight(-23.7)
                .targetWeight(75.6)
                .build()
        );
    }

    @Test
    void createWeightTracker_whenNegativeTargetWeight_thenThrowException() {
        assertThrows(NotValidWeightTrackerException.class, () ->
            WeightTracker.builder()
                .userId(1L)
                .initialWeight(73.7)
                .targetWeight(-45.6)
                .build()
        );
    }
}
