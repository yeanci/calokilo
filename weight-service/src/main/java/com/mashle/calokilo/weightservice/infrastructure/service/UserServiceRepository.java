package com.mashle.calokilo.weightservice.infrastructure.service;

import com.mashle.calokilo.weightservice.domain.ports.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserServiceRepository implements UserRepository {

    private final String USER_MICROSERVICE_URL = "http://user-service:8080/users";
    private final WebClient.Builder webClientBuilder;

    @Override
    public boolean exists(Long userId) {
        UserResource user =  webClientBuilder.build().get()
                .uri(USER_MICROSERVICE_URL + "/{userId}", userId)
                .retrieve()
                .bodyToMono(UserResource.class)
                .onErrorResume(e -> Mono.empty())
                .block();

        return user != null;
    }
}
