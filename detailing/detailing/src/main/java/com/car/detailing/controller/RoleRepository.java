package com.car.detailing.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.detailing.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
