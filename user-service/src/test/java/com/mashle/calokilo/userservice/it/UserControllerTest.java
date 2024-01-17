package com.mashle.calokilo.userservice.it;

import com.mashle.calokilo.userservice.application.responses.GetAllUsersResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UserControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1");

    @Autowired
    WebTestClient webTestClient;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    @Rollback
    void shouldCreateUser_whenValidData_thenCreateUser() {
        // Given
        String validUser = """
                    {
                        "firstName": "John",
                        "email" : "john@gmail.com",
                        "password": "mySecretP@ssword",
                        "birthDate" : "1980-12-01",
                        "height": 180
                    }
                """;

        // When
        WebTestClient.ResponseSpec response = webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(validUser))
                .exchange();

        // Then
        response
                .expectStatus().isCreated()
                .expectBody()
                    .jsonPath("$.id").isEqualTo(11L)
                    .jsonPath("$.firstName").isEqualTo("John")
                    .jsonPath("$.email").isEqualTo("john@gmail.com")
                    .jsonPath("$.password").doesNotExist()
                    .jsonPath("$.birthDate").isEqualTo("1980-12-01")
                    .jsonPath("$.height").isEqualTo(180);
    }

    @Test
    @Rollback
    void shouldCreateUser_whenNotValidData_thenReturnError() {
        // Given
        String invalidUser = """
                    {
                        "firstName": "J",
                        "email" : "john@gmail",
                        "password": "mySecret",
                        "birthDate" : "2040-12-01",
                        "height": -15
                    }
                """;

        // When
        WebTestClient.ResponseSpec response = webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(invalidUser))
                .exchange();

        // Then
        response.expectStatus().is5xxServerError();
    }

    @Test
    void shouldGetAllUsers_whenAtLeastOneUserExists_thenReturnUserList() {
        // When
        WebTestClient.ResponseSpec response = webTestClient.get()
                .uri("/users")
                .exchange();

        // Then
        response.expectStatus().isOk();
        response.expectBody()
                .jsonPath("$.users").isArray()
                .jsonPath("$.users.length()").isEqualTo(10);
    }
}
