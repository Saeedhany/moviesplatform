package com.saeed.moviesplatform.security;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   DaoAuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http
                .authenticationProvider(authenticationProvider)
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                        )
                )

.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/v1/auth/login",
                        "/api/v1/auth/register"
                ).permitAll()

                .requestMatchers("/error").permitAll()

                .requestMatchers(
                        HttpMethod.POST,
                        "/api/v1/movies/*/rating"
                ).authenticated()

                // Movies: Admin only for write operations
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/v1/movies/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/v1/movies/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PATCH,
                        "/api/v1/movies/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.DELETE,
                        "/api/v1/movies/**"
                ).hasRole("ADMIN")

                // Genres: Admin only for write operations
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/v1/genres/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/v1/genres/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PATCH,
                        "/api/v1/genres/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.DELETE,
                        "/api/v1/genres/**"
                ).hasRole("ADMIN")

                // Actors: Admin only for write operations
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/v1/actors/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/v1/actors/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.PATCH,
                        "/api/v1/actors/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        HttpMethod.DELETE,
                        "/api/v1/actors/**"
                ).hasRole("ADMIN")

                // Public read operations
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/v1/movies/**",
                        "/api/v1/genres/**",
                        "/api/v1/actors/**"
                ).permitAll()

                // Authenticated access for everything else
                .anyRequest().authenticated()
        );


        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    // avoid defining the filter as Servlet flter, to not be defined twice (addBefore and servlet)
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilterRegistration(
            JwtAuthenticationFilter filter) {

        FilterRegistrationBean<JwtAuthenticationFilter> registration =
                new FilterRegistrationBean<>(filter);

        registration.setEnabled(false);

        return registration;
    }

}
