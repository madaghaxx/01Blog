package com.madagha.backend.auth.service;

import com.madagha.backend.user.entity.Role;
import com.madagha.backend.user.entity.Status;
import com.madagha.backend.user.entity.User;
import com.madagha.backend.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String email, String rawPassword) {

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

        return userRepository.save(user);
    }
}
