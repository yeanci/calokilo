package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.UserRepository;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;
import com.mashle.calokilo.weightservice.domain.shared.WeightTrackerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class GetWeightTrackerServiceTest {

    private WeightTrackerRepository weightTrackerRepository;
    private UserRepository userRepository;
    private GetWeightTrackerService getWeightTrackerService;

    private WeightTracker validWeightTracker;

    @BeforeEach
    void setup() {
        weightTrackerRepository = mock(WeightTrackerRepository.class);
        userRepository = mock(UserRepository.class);
        getWeightTrackerService = new GetWeightTrackerService(weightTrackerRepository, userRepository);

        validWeightTracker = WeightTracker.builder()
                .userId(1L)
                .initialWeight(85.)
                .targetWeight(78.)
                .weightHistory(List.of(WeightEntry.builder().weight(85.).entryDate(LocalDate.now()).build()))
                .build();
    }

    @Test
    void getWeightTracker_whenUserExists_thenReturnUser() throws UserNotFoundException, WeightTrackerNotFoundException {
        when(userRepository.exists(1L)).thenReturn(true);
        when(weightTrackerRepository.getById(1L)).thenReturn(Optional.of(validWeightTracker));

        // When
        WeightTracker weightTracker = getWeightTrackerService.getWeightTracker(1L);

        // Then
        assertThat(weightTracker).isEqualTo(validWeightTracker);
    }

    @Test
    void getWeightTracker_whenUserDoesNotExist_thenThrowException() {
        when(userRepository.exists(1L)).thenReturn(false);
        when(weightTrackerRepository.getById(1L)).thenReturn(Optional.of(validWeightTracker));

        assertThrows(UserNotFoundException.class, () -> getWeightTrackerService.getWeightTracker(1L));

        verify(weightTrackerRepository, times(0)).getById(1L);
    }

    @Test
    void getWeightTracker_whenWeightTrackerDoesNotExist_thenThrowException() {
        when(userRepository.exists(1L)).thenReturn(true);
        when(weightTrackerRepository.getById(anyLong())).thenReturn(Optional.empty());

        assertThrows(WeightTrackerNotFoundException.class, () -> getWeightTrackerService.getWeightTracker(1L));
    }
}
