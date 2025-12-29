package com.madagha.backend.user.service;

import com.madagha.backend.user.entity.Role;
import com.madagha.backend.user.entity.User;
import com.madagha.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User createUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        if (existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, User user) {
        User existingUser = getUserById(id);
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setAvatar(user.getAvatar());
        existingUser.setRole(user.getRole());
        existingUser.setStatus(user.getStatus());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(UUID id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void purgeUsers() {
        List<User> users = getAllUsers();
        for (User user : users) {
            deleteUser(user.getId());
        }
    }

    @Override
    public void giveAdminRights(UUID id) {
        User admin = getUserById(id);
        admin.setRole(Role.ADMIN);
        updateUser(admin.getId(), admin);
    }

    @Override
    public void removeAdminRights(UUID id) {
        User admin = getUserById(id);
        admin.setRole(Role.USER);
        updateUser(admin.getId(), admin);
    }

}
