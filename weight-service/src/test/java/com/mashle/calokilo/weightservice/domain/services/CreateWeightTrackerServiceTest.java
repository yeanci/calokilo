package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.NotValidWeightTrackerException;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateWeightTrackerServiceTest {

    private WeightTrackerRepository weightTrackerRepository;
    private CreateWeightTrackerService createWeightTrackerService;

    private WeightTracker validWeightTracker;

    @BeforeEach
    void setup() {
        weightTrackerRepository = mock(WeightTrackerRepository.class);
        createWeightTrackerService = new CreateWeightTrackerService(weightTrackerRepository);

        validWeightTracker = WeightTracker.builder()
                .userId(1L)
                .initialWeight(85.6)
                .targetWeight(75.)
                .build();
    }

    @Test
    void createWeightTracker_whenValidData_thenReturnCreatedWeightTracker() {
        when(weightTrackerRepository.save(any(WeightTracker.class))).thenReturn(validWeightTracker);

        // When
        WeightTracker createdWeightTracker = createWeightTrackerService.createWeightTracker(1L, 85.6, 75.);

        // Then
        assertThat(createdWeightTracker).isEqualTo(validWeightTracker);
    }

    @Test
    void createWeightTracker_whenUserDoesNotExist_thenThrowException() {
        assertThrows(UserNotFoundException.class, () ->
                createWeightTrackerService.createWeightTracker(1L, 95.3, 75.)
        );

        verify(weightTrackerRepository, times(0)).save(any(WeightTracker.class));
    }

    @Test
    void createWeightTracker_whenInvalidInitialWeight_thenThrowException() {
        assertThrows(NotValidWeightTrackerException.class, () ->
                createWeightTrackerService.createWeightTracker(1L, -65.3, 75.)
        );

        verify(weightTrackerRepository, times(0)).save(any(WeightTracker.class));
    }

    @Test
    void createWeightTracker_whenInvalidTargetWeight_thenThrowException() {
        assertThrows(NotValidWeightTrackerException.class, () ->
                createWeightTrackerService.createWeightTracker(1L, 85.3, -75.)
        );

        verify(weightTrackerRepository, times(0)).save(any(WeightTracker.class));
    }
}
