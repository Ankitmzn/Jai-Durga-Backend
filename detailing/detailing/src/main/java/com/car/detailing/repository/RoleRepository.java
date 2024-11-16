package com.car.detailing.repository;

import com.car.detailing.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Custom query methods can be added here if needed
}
