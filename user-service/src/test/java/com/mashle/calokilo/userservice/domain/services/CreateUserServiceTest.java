package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserCreatedMessenger;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.services.CreateUserService;
import com.mashle.calokilo.userservice.domain.shared.NotValidUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserServiceTest {

    private UserRepository userRepository;
    private UserCreatedMessenger userCreatedMessenger;
    private CreateUserService createUserService;

    private User validUser;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userCreatedMessenger = mock(UserCreatedMessenger.class);
        createUserService = new CreateUserService(userRepository, userCreatedMessenger);

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
    void createUser_whenValidData_thenReturnCreatedUser() {
        when(userRepository.save(any(User.class))).thenReturn(validUser);
        doNothing().when(userCreatedMessenger).notifyUserCreated(anyLong(), anyDouble(), anyDouble());

        // When
        User createdUser = createUserService.createUser(validUser, 85., 75.);

        // Then
        assertThat(createdUser).isEqualTo(validUser);
    }

    @Test
    void createUser_whenInvalidInitialWeight_thenThrowException() {
        assertThrows(NotValidUserException.class, () ->
                createUserService.createUser(validUser, -85., 75.)
        );

        verify(userRepository, times(0)).save(any(User.class));
        verify(userCreatedMessenger, times(0)).notifyUserCreated(anyLong(), anyDouble(), anyDouble());
    }

    @Test
    void createUser_whenInvalidTargetWeight_thenThrowException() {
        assertThrows(NotValidUserException.class, () ->
                createUserService.createUser(validUser, 85., -75.)
        );

        verify(userRepository, times(0)).save(any(User.class));
        verify(userCreatedMessenger, times(0)).notifyUserCreated(anyLong(), anyDouble(), anyDouble());
    }
}
