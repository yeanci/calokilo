package com.mashle.calokilo.userservice.application.controllers;

import com.mashle.calokilo.userservice.application.requests.CreateUserRequest;
import com.mashle.calokilo.userservice.application.responses.CreateUserResponse;
import com.mashle.calokilo.userservice.application.responses.GetAllUsersResponse;
import com.mashle.calokilo.userservice.domain.ports.CreateUserPort;
import com.mashle.calokilo.userservice.domain.ports.GetAllUsersPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private CreateUserPort createUserPort;
    private GetAllUsersPort getAllUsersPort;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest userRequest) {
        CreateUserResponse saved = CreateUserResponse.from(createUserPort.createUser(userRequest.toUser()));

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersResponse users = GetAllUsersResponse.from(getAllUsersPort.getAllUsers());

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}
