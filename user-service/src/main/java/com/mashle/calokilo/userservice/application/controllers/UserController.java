package com.mashle.calokilo.userservice.application.controllers;

import com.mashle.calokilo.userservice.application.requests.CreateUserRequest;
import com.mashle.calokilo.userservice.application.resources.UserResource;
import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.CreateUserPort;
import com.mashle.calokilo.userservice.domain.ports.GetAllUsersPort;
import com.mashle.calokilo.userservice.domain.ports.GetUserByIdPort;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private CreateUserPort createUserPort;
    private GetAllUsersPort getAllUsersPort;
    private GetUserByIdPort getUserByIdPort;

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserRequest userRequest) {
        UserResource saved = new UserResource(createUserPort.createUser(userRequest.toUser()));

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<UserResource> users = getAllUsersPort.getAllUsers().stream().map(UserResource::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        Optional<User> user = getUserByIdPort.getUserById(id);

        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserResource(user.get()));
        } else {
            throw new NotFoundException();
        }
    }

}
