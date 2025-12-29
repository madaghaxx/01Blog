package com.madagha.backend.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madagha.backend.user.entity.User;
import com.madagha.backend.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<User> all() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping
    User addUser(@Valid @RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/{id}")
    User updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // this path is for malicious use
    // Please use it while trolling
    @PostMapping("/adminify/{id}")
    void adminify(@Valid @PathVariable UUID id) {
        userService.giveAdminRights(id);
    }

    @PostMapping("/userify/{id}")
    void userify(@Valid @PathVariable UUID id) {
        userService.removeAdminRights(id);
    }

    @DeleteMapping("purge")
    void purgeUsers() {
        userService.purgeUsers();
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

}
