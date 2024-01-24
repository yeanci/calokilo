package com.mashle.calokilo.weightservice.application.controllers;

import com.mashle.calokilo.weightservice.domain.ports.CreateWeightTrackerPort;
import com.mashle.calokilo.weightservice.domain.shared.UserNotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserCreatedListener {

    private final CreateWeightTrackerPort createWeightTrackerPort;

    public UserCreatedListener(CreateWeightTrackerPort createWeightTrackerPort) {
        this.createWeightTrackerPort = createWeightTrackerPort;
    }

    @JmsListener(destination = "user.created.queue")
    public void handleUserCreatedEvent(Map<String, Object> message) throws UserNotFoundException {
        Long userId = (Long) message.get("userId");
        Double initialWeight = (Double) message.get("initialWeight");
        Double targetWeight = (Double) message.get("targetWeight");

        createWeightTrackerPort.createWeightTracker(userId, initialWeight, targetWeight);
    }
}
