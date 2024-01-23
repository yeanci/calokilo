package com.mashle.calokilo.userservice.application.controllers;

import com.mashle.calokilo.userservice.application.requests.CreateUserRequest;
import com.mashle.calokilo.userservice.application.resources.UserResource;
import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.CreateUserPort;
import com.mashle.calokilo.userservice.domain.ports.DeleteUserPort;
import com.mashle.calokilo.userservice.domain.ports.GetAllUsersPort;
import com.mashle.calokilo.userservice.domain.ports.GetUserByIdPort;
import com.mashle.calokilo.userservice.domain.shared.NotValidUserException;
import com.mashle.calokilo.userservice.domain.shared.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private CreateUserPort createUserPort;
    private GetAllUsersPort getAllUsersPort;
    private GetUserByIdPort getUserByIdPort;
    private DeleteUserPort deleteUserPort;

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserRequest userRequest,
                                                   @RequestParam double initialWeight,
                                                   @RequestParam double targetWeight) throws NotValidUserException {
        UserResource saved = new UserResource(createUserPort.createUser(userRequest.toUser(), initialWeight, targetWeight));

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<UserResource> users = getAllUsersPort.getAllUsers().stream().map(UserResource::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) throws UserNotFoundException {
        User user = getUserByIdPort.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new UserResource(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        deleteUserPort.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
