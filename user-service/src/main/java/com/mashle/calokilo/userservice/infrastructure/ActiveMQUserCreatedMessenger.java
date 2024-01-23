package com.mashle.calokilo.userservice.infrastructure;

import com.mashle.calokilo.userservice.domain.ports.UserCreatedMessenger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ActiveMQUserCreatedMessenger implements UserCreatedMessenger {

    private final JmsTemplate jmsTemplate;

    public ActiveMQUserCreatedMessenger(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void notifyUserCreated(Long userId, double initialWeight, double targetWeight) {
        jmsTemplate.convertAndSend("user.created.queue",
                Map.of("userId", userId,
                        "initialWeight", initialWeight,
                        "targetWeight", targetWeight)
        );
    }
}
