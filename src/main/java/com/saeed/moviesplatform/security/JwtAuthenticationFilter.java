package com.saeed.moviesplatform.security;

import com.saeed.moviesplatform.BlackListedToken.service.IBlackListedTokenService;
import com.saeed.moviesplatform.entity.PlatformUser;
import com.saeed.moviesplatform.repository.UserRepository;
import com.saeed.moviesplatform.security.util.JwtService;
import com.saeed.moviesplatform.security.util.TokenHashUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final IBlackListedTokenService blackListedTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // extract token and exclude bearer
        String token = authHeader.substring(7);




        try {
            Claims claims = jwtService.getClaims(token);

            String tokenHash = TokenHashUtil.hashToken(token);

            if(blackListedTokenService.isBlackListed(tokenHash)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            Long userId = Long.parseLong(claims.getSubject());

            // get user from DB because the token may be valid but the user deleted
            PlatformUser user = userRepository.findById(userId)
                    .orElse(null);

            if(user == null){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            CustomUserDetails userDetails = new CustomUserDetails(user);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }




        } catch (JwtException | IllegalArgumentException e) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
