package com.mashle.calokilo.userservice.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1");

    @Autowired
    WebTestClient webTestClient;

    final String validUser = """
                    {
                        "firstName": "John",
                        "email" : "john@gmail.com",
                        "password": "mySecretP@ssword",
                        "birthDate" : "1980-12-01",
                        "height": 180
                    }
                """;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void createUser_whenValidData_thenCreateUser() {
        var response = webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(validUser))
                .exchange();

        response.expectStatus().isCreated();

//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(Objects.requireNonNull(response.getBody()).id()).isEqualTo(1);
//        assertThat(response.getBody().firstName()).isEqualTo("John");
//        assertThat(response.getBody().email()).isEqualTo("john@gmail.com");
//        assertThat(response.getBody().birthDate()).isEqualTo("1980-12-01");
//        assertThat(response.getBody().height()).isEqualTo(180);
    }
}
