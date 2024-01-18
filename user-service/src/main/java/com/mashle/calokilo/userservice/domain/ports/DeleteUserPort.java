package com.mashle.calokilo.userservice.domain.ports;

import com.mashle.calokilo.userservice.domain.shared.UserNotFoundException;

public interface DeleteUserPort {

    void deleteUser(Long id) throws UserNotFoundException;
}
