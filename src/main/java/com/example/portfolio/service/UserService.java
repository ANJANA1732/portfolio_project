package com.example.portfolio.service;


import com.example.portfolio.entity.User;
import com.example.portfolio.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void createAdminUser() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Change password in production
            userRepository.save(admin);
        }
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

