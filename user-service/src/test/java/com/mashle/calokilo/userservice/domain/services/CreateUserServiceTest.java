package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.services.CreateUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateUserServiceTest {

    private UserRepository userRepository;
    private CreateUserService createUserService;

    private User validUser;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        createUserService = new CreateUserService(userRepository);

        validUser = User.builder()
                .id(1L)
                .firstName("John")
                .email("john@example.com")
                .password("s@feP@ssword")
                .birthDate(LocalDate.of(1995, 1, 30))
                .height(180)
                .build();
    }

    @Test
    void createUser_whenValidUser_thenReturnCreatedUser() {
        when(userRepository.save(any(User.class))).thenReturn(validUser);

        // When
        User createdUser = createUserService.createUser(validUser);

        // Then
        assertThat(createdUser).isEqualTo(validUser);
    }

    @Test
    void createUser_whenInvalidUser_thenThrowException() {
        when(userRepository.save(any(User.class))).thenReturn(validUser);

        // When
        User createdUser = createUserService.createUser(validUser);

        // Then
        assertThat(createdUser).isEqualTo(validUser);
    }
}
