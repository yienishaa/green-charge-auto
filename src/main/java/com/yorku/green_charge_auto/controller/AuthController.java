package com.yorku.green_charge_auto.controller;


import com.yorku.green_charge_auto.constants.Role;

import com.yorku.green_charge_auto.dto.LoginRequest;
import com.yorku.green_charge_auto.dto.LoginResponse;
import com.yorku.green_charge_auto.service.AuthService;
import com.yorku.green_charge_auto.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private ShoppingCartService shoppingCartService;


    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest request) {
        return authService.registerUser(request.getUsername(), request.getPassword(), Role.USER);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.loginUser(loginRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam int userId) {
        shoppingCartService.clearCartByUserId(userId);
        return ResponseEntity.ok("Cart cleared and user logged out.");

    }

}