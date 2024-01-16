package com.mashle.calokilo.userservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPostgresUserRepository extends JpaRepository<UserEntity, Long> {
}
