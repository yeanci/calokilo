package com.mashle.calokilo.userservice.infrastructure;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@AllArgsConstructor
public class PostgresUserRepository implements UserRepository {

    private final SpringDataPostgresUserRepository springPgUserRepository;

    @Override
    public User save(User user) {
        UserEntity saved = springPgUserRepository.save(new UserEntity(user));

        return saved.toCreatedUser();
    }
}
