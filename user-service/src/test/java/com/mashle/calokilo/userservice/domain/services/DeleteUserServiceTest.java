package com.mashle.calokilo.userservice.domain.services;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.services.DeleteUserService;
import com.mashle.calokilo.userservice.domain.services.GetUserByIdService;
import com.mashle.calokilo.userservice.domain.shared.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DeleteUserServiceTest {

    private UserRepository userRepository;
    private DeleteUserService deleteUserService;

    private User validUser;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        deleteUserService = new DeleteUserService(userRepository);

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
    void deleteUser_whenIdValid_thenDeleteUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(validUser));

        // When
        deleteUserService.deleteUser(1L);

        // Then
        verify(userRepository, times(1)).delete(validUser.id());
    }

    @Test
    void deleteUser_whenIdInvalid_thenThrowException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());


        assertThrows(UserNotFoundException.class, () -> deleteUserService.deleteUser(68L));
        verify(userRepository, times(0)).delete(validUser.id());
    }
}
