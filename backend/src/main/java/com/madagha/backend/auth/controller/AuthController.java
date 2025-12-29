package com.madagha.backend.auth.controller;

import com.madagha.backend.auth.dto.RegisterRequest;
import com.madagha.backend.auth.service.AuthService;
import com.madagha.backend.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequest request) {
        authService.register(
                request.getUsername(),
                request.getEmail(),
                request.getPassword());
    }
}
