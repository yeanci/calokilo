package com.mashle.calokilo.weightservice.domain;

import com.mashle.calokilo.weightservice.domain.shared.NotValidWeightEntryException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeightEntryTest {

    @Test
    void createWeightEntry_whenValidData_thenCreate() {
        assertDoesNotThrow(() ->
            WeightEntry.builder()
                .weight(86.4)
                .entryDate(LocalDate.now())
                .build()
        );
    }

    @Test
    void createWeightEntry_whenEntryDateIsInTheFuture_thenThrowException() {
        assertThrows(NotValidWeightEntryException.class, () ->
            WeightEntry.builder()
                .weight(86.4)
                .entryDate(LocalDate.now().plusMonths(3L))
                .build()
        );
    }

    @Test
    void createWeightEntry_whenWeightIsInvalid_thenThrowException() {
        assertThrows(NotValidWeightEntryException.class, () ->
            WeightEntry.builder()
                .weight(-82.8)
                .entryDate(LocalDate.now())
                .build()
        );
    }
}
