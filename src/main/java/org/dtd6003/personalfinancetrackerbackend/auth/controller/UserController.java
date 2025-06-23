package org.dtd6003.personalfinancetrackerbackend.auth.controller;

import jakarta.validation.Valid;
import org.dtd6003.personalfinancetrackerbackend.auth.dto.AuthResponse;
import org.dtd6003.personalfinancetrackerbackend.auth.dto.LoginRequest;
import org.dtd6003.personalfinancetrackerbackend.auth.dto.RegisterRequest;
import org.dtd6003.personalfinancetrackerbackend.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final AuthService service;
    public UserController(AuthService service){
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req){
        AuthResponse response = service.createUser(req);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req){
        AuthResponse response = service.login(req);
        return ResponseEntity.ok(response);
    }

}
