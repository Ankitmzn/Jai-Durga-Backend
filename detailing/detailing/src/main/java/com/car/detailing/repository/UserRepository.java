package com.car.detailing.repository;

import com.car.detailing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
