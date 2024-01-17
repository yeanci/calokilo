package com.mashle.calokilo.userservice.ut;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.infrastructure.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UserEntityTest {

    @Test
    void shouldMapUserEntityToUser() {
        // Given
        UserEntity userEntity = new UserEntity(1L,
                "John",
                "john@gmail.com",
                "mySecretP@ssword",
                LocalDate.of(1980, 12, 1),
                180);

        // When
        User user = userEntity.toCreatedUser();

        // Then
        assertThat(user.id()).isEqualTo(1L);
        assertThat(user.firstName()).isEqualTo("John");
        assertThat(user.email()).isEqualTo("john@gmail.com");
        assertThat(user.password()).isEqualTo("mySecretP@ssword");
        assertThat(user.birthDate()).isEqualTo(LocalDate.of(1980, 12, 1));
        assertThat(user.height()).isEqualTo(180);
    }
}
