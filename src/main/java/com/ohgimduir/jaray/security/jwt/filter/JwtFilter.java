package com.ohgimduir.jaray.security.jwt.filter;

import com.ohgimduir.jaray.security.jwt.helper.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {
        final String token = jwtHelper.extractTokenFromRequest(request);

        if(token != null) {
            SecurityContextHolder.getContext().setAuthentication(jwtHelper.getAuthentication(token));
        }

        chain.doFilter(request, response);
    }

}