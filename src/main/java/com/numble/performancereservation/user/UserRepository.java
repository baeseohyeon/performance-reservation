package com.numble.performancereservation.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndType(Long id, ProducerType type);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
