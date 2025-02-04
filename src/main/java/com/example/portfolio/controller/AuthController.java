package com.example.portfolio.controller;

import com.example.portfolio.entity.Users;
import com.example.portfolio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    // POST for login - Using HTTP session
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest, HttpSession session) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        // Authenticate user (you may replace this with your own authentication logic)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Set session flag
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("username", username);
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "username", username,
                    "timestamp", new Date()
            ));
        } else {
            return ResponseEntity.status(401).body(Map.of(
                    "message", "Invalid credentials",
                    "timestamp", new Date()
            ));
        }
    }


    // To check if user is authenticated
    @GetMapping("/check-session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn != null && isLoggedIn) {
            return ResponseEntity.ok(Map.of("message", "User is logged in"));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "User not logged in"));
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<?> protectedEndpoint(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn != null && isLoggedIn) {
            return ResponseEntity.ok(Map.of("message", "This is a protected endpoint"));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Access denied"));
        }
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
