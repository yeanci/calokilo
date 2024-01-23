package com.mashle.calokilo.weightservice.domain.ports;

public interface UserRepository {

    boolean exists(Long userId);
}
