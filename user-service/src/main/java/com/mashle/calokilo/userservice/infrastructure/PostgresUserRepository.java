package com.mashle.calokilo.userservice.infrastructure;

import com.mashle.calokilo.userservice.domain.User;
import com.mashle.calokilo.userservice.domain.ports.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PostgresUserRepository implements UserRepository {

    private final SpringDataPostgresUserRepository springPgUserRepository;

    @Override
    public User save(User user) {
        UserEntity saved = springPgUserRepository.save(new UserEntity(user));

        return saved.toUser();
    }

    @Override
    public List<User> findAll() {
        return springPgUserRepository.findAll().stream().map(UserEntity::toUser).toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return springPgUserRepository.findById(id).map(UserEntity::toUser);
    }

    @Override
    public void delete(Long id) {
        springPgUserRepository.deleteById(id);
    }
}
