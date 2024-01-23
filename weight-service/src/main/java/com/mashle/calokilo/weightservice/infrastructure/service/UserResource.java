package com.mashle.calokilo.weightservice.infrastructure.service;

import java.time.LocalDate;

public record UserResource(Long id,
                           String firstName,
                           String email,
                           LocalDate birthDate,
                           int height) {
}
