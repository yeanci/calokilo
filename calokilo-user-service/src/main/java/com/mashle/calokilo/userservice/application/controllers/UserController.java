package com.mashle.calokilo.userservice.application.controllers;

import com.mashle.calokilo.userservice.domain.ports.CreateUserPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private CreateUserPort createUserPort;

    @PostMapping
    public ResponseEntity<UserResource> createUser(CreateUserRequest userRequest) {
        UserResource saved = UserResource.from(createUserPort.createUser(userRequest.toUser()));

        return ResponseEntity.ok().body(saved);
    }

}
