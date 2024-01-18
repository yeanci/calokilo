package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.services.GetUserByIdService;
import com.mashle.calokilo.userservice.domain.shared.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetUserByIdServiceTest {

    private UserRepository userRepository;
    private GetUserByIdService getUserByIdService;

    private User validUser;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getUserByIdService = new GetUserByIdService(userRepository);

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
    void getUserById_whenIdValid_thenReturnUser() throws UserNotFoundException {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(validUser));

        // When
        User user = getUserByIdService.getUserById(1L);

        // Then
        assertThat(user).isEqualTo(validUser);
    }

    @Test
    void getUserById_whenIdNotValid_thenReturnEmptyOptional() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> getUserByIdService.getUserById(56L));
    }
}
