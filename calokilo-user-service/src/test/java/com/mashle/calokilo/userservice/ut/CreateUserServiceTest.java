package com.mashle.calokilo.userservice.ut;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import com.mashle.calokilo.userservice.domain.services.CreateUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateUserServiceTest {

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

        // when
        User createdUser = createUserService.createUser(validUser);

        // then
        assertThat(createdUser).isEqualTo(validUser);
    }
}
