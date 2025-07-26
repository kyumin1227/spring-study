package dev.be.sns.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, 
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {
        
        log.error("Authentication failed: {}", authException.getMessage());
        
        response.setStatus(ErrorCode.INVALID_TOKEN.getStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String errorResponse = "{" +
                "\"resultCode\":" + "\"" + ErrorCode.INVALID_TOKEN + "\"," +
                "\"result\":" + null + "}";
        
        response.getWriter().write(errorResponse);
    }
}
