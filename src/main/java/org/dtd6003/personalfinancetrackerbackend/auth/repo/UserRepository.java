package org.dtd6003.personalfinancetrackerbackend.auth.repo;

import org.dtd6003.personalfinancetrackerbackend.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean findByEmail(String email);
}
