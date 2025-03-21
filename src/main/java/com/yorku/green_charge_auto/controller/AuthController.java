package com.yorku.green_charge_auto.controller;

import com.yorku.green_charge_auto.model.Role;
import com.yorku.green_charge_auto.service.AuthService;
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
    public ResponseEntity<Map<String, String>> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam Role role) {
        String token = authService.registerUser(username, password, role);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestParam String username, @RequestParam String password) {
        String token = authService.loginUser(username, password);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
