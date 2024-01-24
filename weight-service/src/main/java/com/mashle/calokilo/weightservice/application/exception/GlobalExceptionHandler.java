package com.mashle.calokilo.weightservice.application.exception;

import com.mashle.calokilo.weightservice.domain.shared.NotFoundException;
import com.mashle.calokilo.weightservice.domain.shared.NotValidWeightEntryException;
import com.mashle.calokilo.weightservice.domain.shared.NotValidWeightTrackerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    @ExceptionHandler({NotValidWeightEntryException.class})
    public ResponseEntity<Object> handleNotValidWeightEntryException(NotValidWeightEntryException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler({NotValidWeightTrackerException.class})
    public ResponseEntity<Object> handleNotValidWeightTrackerException(NotValidWeightTrackerException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
}
