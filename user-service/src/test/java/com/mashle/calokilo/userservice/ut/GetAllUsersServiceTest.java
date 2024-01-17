package com.mashle.calokilo.userservice.ut;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.services.GetAllUsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetAllUsersServiceTest {

    private UserRepository userRepository;
    private GetAllUsersService getAllUsersService;

    private User validUser;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getAllUsersService = new GetAllUsersService(userRepository);

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
    void getAllUsers_whenAtLeastOneUserExists_thenReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(validUser));

        // when
        var users = getAllUsersService.getAllUsers();

        // then
        assertThat(users).isEqualTo(List.of(validUser));
    }

    @Test
    void getAllUsers_whenNoUserExists_thenReturnEmptyListOfUsers() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        var users = getAllUsersService.getAllUsers();

        // then
        assertThat(users).isEmpty();
    }
}
