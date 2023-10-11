package com.ohgimduir.jaray.security.oauth.config;

import com.ohgimduir.jaray.security.jwt.filter.JwtExceptionFilter;
import com.ohgimduir.jaray.security.jwt.filter.JwtFilter;
import com.ohgimduir.jaray.security.oauth.handler.OAuthFailureHandler;
import com.ohgimduir.jaray.security.oauth.handler.OAuthSuccessHandler;
import com.ohgimduir.jaray.security.oauth.service.OAuthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final OAuthFailureHandler oAuthFailureHandler;
    private final OAuthMemberService oAuthMemberService;
    private final JwtFilter jwtFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtFilter.class)

                .authorizeHttpRequests()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()

                .anyRequest().authenticated()

                .and()
                .formLogin().disable()
                .exceptionHandling()

                .and()
                .oauth2Login()
                .successHandler(oAuthSuccessHandler)
                .failureHandler(oAuthFailureHandler)
                .userInfoEndpoint()
                .userService(oAuthMemberService);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}