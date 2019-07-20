package com.kobobook.www.admin.config;

import com.kobobook.www.admin.exception.IdPasswordNotMatchingException;
import com.kobobook.www.admin.exception.UnAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        if (exception instanceof IdPasswordNotMatchingException) {
            request.getRequestDispatcher("/members/login-error").forward(request, response);
        } else if (exception instanceof UnAuthException) {
            request.getRequestDispatcher("/members/login-auth-error").forward(request, response);
        } else {
            log.debug("authFailureHandler error");
            request.getRequestDispatcher("/members/login").forward(request, response);
        }

    }
}
