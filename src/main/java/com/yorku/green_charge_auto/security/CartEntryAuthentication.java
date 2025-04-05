package com.yorku.green_charge_auto.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CartEntryAuthentication implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        if (request.getRequestURI().contains("/shopping-cart/add-to-cart")) {
            response.sendRedirect("/auth/register");
        } else {
            response.sendRedirect("/auth/login");
        }
    }
}
