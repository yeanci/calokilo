package com.mashle.calokilo.weightservice.domain.shared;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User has not been found");
    }
}
