package com.ohgimduir.jaray.security.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgimduir.jaray.common.exception.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            setErrorResponse(response, 401, "Expired jwt");
        } catch (MalformedJwtException e) {
            setErrorResponse(response, 400, "Malformed jwt");
        } catch (UnsupportedJwtException e) {
            setErrorResponse(response, 400, "Unsupported jwt");
        } catch (IllegalArgumentException e) {
            log.error("IllegalArgumentException message : {}", e.getMessage());

            setErrorResponse(response, 400, "IllegalArgumentException occurred");
        }
    }

    private void setErrorResponse(HttpServletResponse response, int status, String message) {
        try {
            responseToClient(response, ErrorResponse.of(status, message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.status());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}