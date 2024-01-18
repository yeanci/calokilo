package com.mashle.calokilo.userservice.domain.shared;

public class NotValidUserException extends IllegalArgumentException {

    public NotValidUserException(String message) {
        super(message);
    }
}
