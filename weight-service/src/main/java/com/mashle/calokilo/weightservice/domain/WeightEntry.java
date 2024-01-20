package com.mashle.calokilo.weightservice.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WeightEntry(Long id,
                          double weight,
                          LocalDate entryDate) {
}
