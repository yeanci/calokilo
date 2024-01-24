package com.mashle.calokilo.weightservice.domain.services;

import com.mashle.calokilo.weightservice.domain.WeightEntry;
import com.mashle.calokilo.weightservice.domain.ports.UserRepository;
import com.mashle.calokilo.weightservice.domain.ports.WeightTrackerRepository;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddWeightEntryServiceTest {

    private WeightTrackerRepository weightTrackerRepository;
    private UserRepository userRepository;
    private AddWeightEntryService addWeightEntryService;

    private WeightEntry validWeightEntry;

    @BeforeEach
    void setup() {
        weightTrackerRepository = mock(WeightTrackerRepository.class);
        userRepository = mock(UserRepository.class);
        addWeightEntryService = new AddWeightEntryService(weightTrackerRepository, userRepository);

        validWeightEntry = WeightEntry.builder()
                .weight(69.)
                .entryDate(LocalDate.now().minusDays(3L))
                .build();
    }

    @Test
    void addWeightEntry_whenValidWeightEntry_thenAddWeightEntry() {
        when(userRepository.exists(1L)).thenReturn(true);
        when(weightTrackerRepository.addWeightEntry(anyLong(), any(WeightEntry.class))).thenReturn(validWeightEntry);

        // When
        WeightEntry addedWeightEntry = addWeightEntryService.addWeightEntry(1L, validWeightEntry);

        // Then
        assertThat(addedWeightEntry).isEqualTo(validWeightEntry);
    }

    @Test
    void addWeightEntry_whenUserDoesNotExist_thenThrowException() {
        when(userRepository.exists(1L)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () ->
                weightTrackerRepository.addWeightEntry(1L, validWeightEntry)
        );

        verify(weightTrackerRepository, times(0)).addWeightEntry(1L, validWeightEntry);
    }
}
