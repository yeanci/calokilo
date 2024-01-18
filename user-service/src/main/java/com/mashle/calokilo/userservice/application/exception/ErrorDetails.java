package com.mashle.calokilo.userservice.application.exception;

import java.util.Date;

public record ErrorDetails(Date timestamp,
                           String message,
                           String details) {
}
