package com.ohgimduir.jaray.auth.exception.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgimduir.jaray.common.exception.CustomException;
import com.ohgimduir.jaray.common.exception.ErrorResponse;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            log.error("CustomException message : {}", e.getMessage());

            setErrorResponse(response, e.getStatus(), e.getMessage());
        } catch (SignatureException e) {
            log.error("SignatureException message : {}", e.getMessage());

            setErrorResponse(response, 400, "Jwt signature does not matched");
        } catch (Exception e) {
            log.error("ErrorName : {}", e.getClass().getSimpleName());
            log.error("ErrorMessage : {}", e.getMessage());

            setErrorResponse(response, 500, "Uncaught exception occurred");
        }
    }

    private void setErrorResponse(HttpServletResponse response, int status, String message) {
        try {
            responseToClient(response, ErrorResponse.of(status, message));
        } catch (IOException e) {
            log.error("IOException occurred : {}", e.getMessage());
        }
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.status());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}