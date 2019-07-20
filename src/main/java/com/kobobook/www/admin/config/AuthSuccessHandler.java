package com.kobobook.www.admin.config;

import com.kobobook.www.admin.domain.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()))) {
            response.sendRedirect("/admin/dashboard");
        } else {
            response.sendRedirect("/members/login");
        }
    }
}
