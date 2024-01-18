package com.mashle.calokilo.userservice.domain.shared;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User has not been found");
    }
}
