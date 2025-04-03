package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.constants.Role;
import com.yorku.green_charge_auto.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterRequest request) {
        String token = authService.registerUser(request.getUsername(), request.getPassword(), request.getRole());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginRequest request) throws Exception {
        String token = authService.loginUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @Data
    static class RegisterRequest {
        private String username;
        private String password;
        private Role role;
    }

    @Data
    static class LoginRequest {
        private String username;
        private String password;
    }
}
