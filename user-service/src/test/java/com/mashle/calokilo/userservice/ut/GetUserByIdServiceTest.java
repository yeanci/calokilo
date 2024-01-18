package com.mashle.calokilo.userservice.ut;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.services.GetUserByIdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void getUserById_whenIdValid_thenReturnUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(validUser));

        // when
        Optional<User> user = getUserByIdService.getUserById(1L);

        // then
        assertThat(user).contains(validUser);
    }

    @Test
    void getUserById_whenIdNotValid_thenReturnEmptyOptional() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // when
        Optional<User> user = getUserByIdService.getUserById(56L);

        // then
        assertThat(user).isEmpty();
    }
}
