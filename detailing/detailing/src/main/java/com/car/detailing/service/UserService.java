package com.car.detailing.service;

import com.car.detailing.model.LoginRequest;
import com.car.detailing.model.User;
import com.car.detailing.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register user with encoded password
    public void registerUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Encrypt the password before saving it
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Save user and ensure password is encoded
    public boolean saveUser(User user) {
        // Encrypt password if it's not already encoded
        if (!user.getPassword().startsWith("$2a$")) { // Check if password is not BCrypt
            user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        }
        userRepository.save(user);
        return true; // Return true on successful save
    }

    // Check if user exists by username or email
    public boolean userExists(String username, String email) {
        return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
    }

    // Authenticate user using encoded password
    public boolean authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return true; // Password matches
        }
        return false; // Invalid credentials
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	public User getUserById(Object userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
