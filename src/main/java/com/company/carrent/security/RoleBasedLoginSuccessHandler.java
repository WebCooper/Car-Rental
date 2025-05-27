package com.company.carrent.security;

import com.company.carrent.entity.User;

import io.jmix.core.security.CurrentAuthentication;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleBasedLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) currentAuthentication.getUser();

        if (user.getRole() == null) {
            // If user or role is null, redirect to a default or error page
            response.sendRedirect("/"); // or use a dedicated error page
            return;
        }

        String redirectUrl = switch (user.getRole()) {
            case CUSTOMER -> "/customer";
            case STAFF -> "/staff";
            case ADMIN -> "/admin";
        };

        response.sendRedirect(redirectUrl);
    }
}
