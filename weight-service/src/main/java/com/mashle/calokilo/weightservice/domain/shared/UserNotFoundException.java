package com.mashle.calokilo.weightservice.domain.shared;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("User has not been found");
    }
}
