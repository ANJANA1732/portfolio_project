package com.example.portfolio.service;
import com.example.portfolio.entity.Users;  // Import your custom model Users
import com.example.portfolio.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;  // Use Spring Security's User class directly
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Users users = userOptional.get();
        return new User(  // Directly use Spring Security's User
                users.getUsername(),
                users.getPassword(),
                Collections.emptyList() // No roles, so empty list
        );
    }
}
