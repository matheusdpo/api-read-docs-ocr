package com.lumen.auth.v1.utils.jwt;


import com.lumen.commons.utils.LogUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class utils to generate and validate JWT token for authentication and authorization purposes in the application.
 *
 * @author matheusdpo
 * @version 1.0.0
 * @since 2025-03
 */
@Component
public class JwtUtils {

    /**
     * Secret key used to sign the JWT token.
     */
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Expiration time of the token in milliseconds.
     */
    private static final long EXPIRATION_TIME = 864_000_000;

    /**
     * Logger instance to log messages.
     */
    @Autowired
    private LogUtils logger;

    /**
     * Generate a JWT token for the user.
     *
     * @param userDetails User details to generate the token.
     * @return JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Create a JWT token with the claims and subject.
     *
     * @param claims  Claims to be added to the token.
     * @param subject Subject of the token.
     * @return JWT token.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Validate the JWT token.
     *
     * @param token       Token to be validated.
     * @param userDetails User details to be validated.
     * @return True if the token is valid, false otherwise.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extract the username from the JWT token.
     *
     * @param token Token to extract the username.
     * @return Username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract the expiration date from the JWT token.
     *
     * @param token Token to extract the expiration date.
     * @return Expiration date extracted from the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract the claim from the JWT token.
     *
     * @param token          Token to extract the claim.
     * @param claimsResolver Function to resolve the claim.
     * @param <T>            Type of the claim.
     * @return Claim extracted from the token.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims from the JWT token.
     *
     * @param token Token to extract the claims.
     * @return Claims extracted from the token.
     */
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            logger.error("There was an error extracting claims from the token: " + e.getMessage());
            throw new RuntimeException("There was an error extracting claims from the token");
        }
    }

    /**
     * Check if the token is expired.
     *
     * @param token Token to be checked.
     * @return True if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}