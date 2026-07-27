package com.example.codebrawl.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        log.info("ok well up to here the filter is being called");
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Authorization Header: {}", authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String jwtToken = authHeader.substring(7);


            Claims claims = authUtil.validateAccessToken(jwtToken);

            String username = claims.getSubject();
            log.info("we got the username: {}", username);

            if (username == null || username.isBlank()) {
                throw new JwtException("JWT does not contain a valid subject.");
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                CustomUserDetails userDetails =
                        (CustomUserDetails) customUserDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Authentication successful for user: {}", username);
            }
        }

        filterChain.doFilter(request, response);
    }
}