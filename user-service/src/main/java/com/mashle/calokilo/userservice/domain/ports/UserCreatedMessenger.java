package com.mashle.calokilo.userservice.domain.ports;

public interface UserCreatedMessenger {

    void notifyUserCreated(Long userId, double initialWeight, double targetWeight);
}
