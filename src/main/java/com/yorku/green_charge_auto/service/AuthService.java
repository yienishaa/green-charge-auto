package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.constants.Role;
import com.yorku.green_charge_auto.dto.LoginRequest;
import com.yorku.green_charge_auto.dto.LoginResponse;
import com.yorku.green_charge_auto.model.LoginUser;
import com.yorku.green_charge_auto.repository.LoginUserRepository;
import com.yorku.green_charge_auto.security.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailSenderService emailSenderService;

    private final AuthenticationConfiguration authConfig;

    public AuthService(AuthenticationConfiguration authConfig) {
        this.authConfig = authConfig;
    }

    public String registerUser(String email, Role role) {

        Optional<LoginUser> existingUser = loginUserRepository.findByUsername(email);

        if (existingUser.isPresent()) {
            throw new RuntimeException("Username exists");
        }

        String tempPassword = UUID.randomUUID().toString().substring(0, 8);

        LoginUser newUser = new LoginUser(email, tempPassword, role);
        loginUserRepository.save(newUser);

        emailSenderService.sendSimpleEmail(email, tempPassword);

        return jwtUtil.generateToken(email);
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        LoginUser user = loginUserRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new LoginResponse(token, user.getId(), user.getRole().name());
    }

    public void resetPassword(String email) {
        LoginUser user = loginUserRepository.findByUsername(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String tempPassword = UUID.randomUUID().toString();
        user.setPassword(tempPassword);
        loginUserRepository.save(user);
        emailSenderService.sendResetPasswordEmail(email, tempPassword);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String trimmed = username.trim();
        Optional<LoginUser> user = loginUserRepository.findByUsername(trimmed);

        if (user.isEmpty()) {
            System.out.println("No user found with username: '" + trimmed + "'");
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("Found user: " + user.get().getUsername());

        return User.withUsername(user.get().getUsername())
                .password(user.get().getPassword())
                .roles(user.get().getRole().name())
                .build();
    }
}