package org.dtd6003.personalfinancetrackerbackend.auth.repo;

import org.dtd6003.personalfinancetrackerbackend.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
