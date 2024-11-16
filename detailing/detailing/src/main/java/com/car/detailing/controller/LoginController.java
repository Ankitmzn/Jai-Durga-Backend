package com.car.detailing.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @GetMapping("/api/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Invalid username or password.");
        }
        return "login"; // Return the name of the login view
    }
}


/*
@RestController
@RequestMapping("/api") // Add /api prefix for all API endpoints
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend requests
public class LoginController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Check if the user exists in the database
        User existingUser = userService.findUserByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}*/
