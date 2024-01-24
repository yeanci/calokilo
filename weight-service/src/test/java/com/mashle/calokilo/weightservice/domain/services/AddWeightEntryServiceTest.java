package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.WeightTracker;
import com.mashle.calokilo.weightservice.domain.ports.UserRepository;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.NotFoundException;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;
import com.mashle.calokilo.weightservice.domain.shared.WeightTrackerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddWeightEntryServiceTest {

    private WeightTrackerRepository weightTrackerRepository;
    private UserRepository userRepository;
    private AddOrUpdateWeightEntryService addWeightEntryService;

    private WeightTracker weightTracker;
    private WeightEntry validWeightEntry;

    @BeforeEach
    void setup() {
        weightTrackerRepository = mock(WeightTrackerRepository.class);
        userRepository = mock(UserRepository.class);
        addWeightEntryService = new AddOrUpdateWeightEntryService(weightTrackerRepository, userRepository);

        validWeightEntry = WeightEntry.builder()
                .weight(69.)
                .entryDate(LocalDate.now().minusDays(3L))
                .build();

        weightTracker = WeightTracker.builder()
                .userId(1L)
                .initialWeight(72.)
                .targetWeight(65.)
                .weightHistory(List.of(validWeightEntry, WeightEntry.builder().weight(72.).entryDate(LocalDate.now().minusDays(4L)).build()))
                .build();
    }

    @Test
    void addWeightEntry_whenValidWeightEntry_thenAddWeightEntry() throws NotFoundException {
        when(userRepository.exists(1L)).thenReturn(true);
        when(weightTrackerRepository.getById(1L)).thenReturn(Optional.of(weightTracker));
        when(weightTrackerRepository.save(any(WeightTracker.class))).thenReturn(weightTracker);

        // When
        WeightEntry addedWeightEntry = addWeightEntryService.addOrUpdateWeightEntry(1L, validWeightEntry);

        // Then
        assertThat(addedWeightEntry).isEqualTo(validWeightEntry);
    }

    @Test
    void addWeightEntry_whenUserDoesNotExist_thenThrowException() {
        when(userRepository.exists(1L)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () ->
                addWeightEntryService.addOrUpdateWeightEntry(1L, validWeightEntry)
        );

        verify(weightTrackerRepository, times(0)).getById(1L);
        verify(weightTrackerRepository, times(0)).save(any(WeightTracker.class));
    }

    @Test
    void addWeightEntry_whenWeightTrackerNotFound_thenThrowException() {
        when(userRepository.exists(1L)).thenReturn(true);
        when(weightTrackerRepository.getById(1L)).thenReturn(Optional.empty());

        assertThrows(WeightTrackerNotFoundException.class, () ->
                addWeightEntryService.addOrUpdateWeightEntry(1L, validWeightEntry)
        );

        verify(weightTrackerRepository, times(0)).save(any(WeightTracker.class));
    }
}
