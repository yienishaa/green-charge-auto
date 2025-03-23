package com.yorku.green_charge_auto.service;

import com.yorku.green_charge_auto.model.LoginUser;
import com.yorku.green_charge_auto.constants.Role;
import com.yorku.green_charge_auto.repository.LoginUserRepository;
import com.yorku.green_charge_auto.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final AuthenticationConfiguration authConfig;

    public AuthService(AuthenticationConfiguration authConfig) {
        this.authConfig = authConfig;
    }

    public String registerUser(String username, String password, Role role) {
        LoginUser user = new LoginUser(username, password, role);
        loginUserRepository.save(user);
        return jwtUtil.generateToken(username);
    }

    public String loginUser(String username, String password) throws Exception {
        AuthenticationManager authenticationManager = authConfig.getAuthenticationManager();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginUser> user = loginUserRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(user.get().getUsername()).password(user.get().getPassword()).roles(user.get().getRole().name()).build();
    }
}