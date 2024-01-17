package com.mashle.calokilo.userservice.application.controllers;

import com.mashle.calokilo.userservice.application.requests.CreateUserRequest;
import com.mashle.calokilo.userservice.application.responses.CreateUserResponse;
import com.mashle.calokilo.userservice.domain.ports.CreateUserPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private CreateUserPort createUserPort;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest userRequest) {
        CreateUserResponse saved = CreateUserResponse.from(createUserPort.createUser(userRequest.toUser()));

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
