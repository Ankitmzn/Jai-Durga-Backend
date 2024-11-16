package com.car.detailing.controller;


import com.car.detailing.model.Role;
import com.car.detailing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private RoleRepository roleRepository;  // Make sure RoleRepository is autowired

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleRepository.findAll();  // Fetch all roles from the database
    }
}


/*import com.car.detailing.model.Role;
import com.car.detailing.model.User;
import com.car.detailing.service.NotificationService;
//import com.car.detailing.repository.RoleRepository;
import com.car.detailing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        // Assign roles to user
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("USER"));  // Assign a default role
        user.setRoles(roles);

        // Save user to DB
        User savedUser = userRepository.save(user);

        // Send notification to the user
        notificationService.sendNotification(savedUser, "Welcome, " + savedUser.getUsername() + "!");

        return savedUser;
    }
}*/
