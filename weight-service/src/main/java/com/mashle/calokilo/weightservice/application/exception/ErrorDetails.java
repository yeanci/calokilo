package com.mashle.calokilo.weightservice.application.exception;

import java.util.Date;

public record ErrorDetails(Date timestamp,
                           String message,
                           String details) {
}
