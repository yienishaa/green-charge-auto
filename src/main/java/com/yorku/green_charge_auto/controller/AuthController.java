package com.yorku.green_charge_auto.controller;


import com.yorku.green_charge_auto.constants.Role;
import com.yorku.green_charge_auto.dto.LoginRequest;
import com.yorku.green_charge_auto.dto.LoginResponse;
import com.yorku.green_charge_auto.service.AuthService;
import com.yorku.green_charge_auto.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailSenderService emailSenderService;


    @PostMapping("/register/{email}")
    public ResponseEntity<?> register(@PathVariable String email) {
        try {
            String token = authService.registerUser(email, Role.USER);
            return ResponseEntity.ok().body(Map.of(
                    "message", "Registration successful, temporary password sent to email.",
                    "token", token
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", e.getMessage()
            ));
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam int userId) {
        //shoppingCartService.clearCartByUserId(userId);
        return ResponseEntity.ok("User logged out.");
    }

    @PostMapping("/reset-password/{email}")
    public void resetPassword(@PathVariable String email) {
        authService.resetPassword(email);
    }



}