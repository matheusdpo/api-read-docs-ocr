package com.lumen.auth.v1.filter;

import com.lumen.auth.v1.services.ApiKeyService;
import com.lumen.auth.v1.services.CustomUserDetailsService;
import com.lumen.auth.v1.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * Class that filters requests to check if the JWT token is valid, and if the user has the necessary permissions.
 * If the token is valid, the user is authenticated.
 *
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    /**
     * CustomUserDetailsService instance.
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * JwtUtils instance.
     */
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * ApiKeyService instance.
     */
    @Autowired
    private ApiKeyService apiKeyService;

    /**
     * Method that filters requests to check if the JWT token is valid, and if the user has the necessary permissions.
     * If the token is valid, the user is authenticated.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param chain    FilterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        //ignores the filter for the register endpoint
        if (path.equals("/register")) {
            chain.doFilter(request, response);
            return;
        }

        //get the authorization header
        String authorizationHeader = request.getHeader("Authorization");

        //initialize username and jwt
        String username = null;
        String jwt = null;

        //check if the authorization header is not null and starts with "Bearer "
        if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtils.extractUsername(jwt);
        }

        //check if the username is not null and the user is not authenticated
        if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        //do the filter chain normally after the token validation and user authentication for the request
        chain.doFilter(request, response);
    }
}