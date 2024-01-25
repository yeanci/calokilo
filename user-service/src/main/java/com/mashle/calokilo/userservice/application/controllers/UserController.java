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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create a user")
    @ApiResponse(responseCode = "201", description = "Created user")
    @ApiResponse(responseCode = "400", description = "Invalid value provided")
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserRequest userRequest,
                                                   @Parameter(description = "Initial weight", example = "80")
                                                   @RequestParam double initialWeight,
                                                   @Parameter(description = "Target weight", example = "75")
                                                   @RequestParam double targetWeight) throws NotValidUserException {
        UserResource saved = new UserResource(createUserPort.createUser(userRequest.toUser(), initialWeight, targetWeight));

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "Found users")
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<UserResource> users = getAllUsersPort.getAllUsers().stream().map(UserResource::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Operation(summary = "Get user by id")
    @ApiResponse(responseCode = "200", description = "Found user")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@Parameter(description = "User id", example = "1")
                                                    @PathVariable Long id) throws UserNotFoundException {
        User user = getUserByIdPort.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new UserResource(user));
    }

    @Operation(summary = "Delete user by id")
    @ApiResponse(responseCode = "200", description = "Deleted user")
    @ApiResponse(responseCode = "404", description = "User not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Parameter(description = "User id", example = "1")
                                           @PathVariable Long id) throws UserNotFoundException {
        deleteUserPort.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
