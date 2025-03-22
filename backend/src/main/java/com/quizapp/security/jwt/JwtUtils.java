package com.quizapp.security.jwt;

import com.quizapp.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final JwtConfigurationProperties jwtConfigProperties;

    @Autowired
    public JwtUtils(JwtConfigurationProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
    }

    /**
     * Generate a JWT token for the authenticated user.
     */
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtConfigProperties.getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, jwtConfigProperties.getJwtSecret())
                .compact();
    }

    /**
     * Retrieve the username from the JWT token.
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                   .setSigningKey(jwtConfigProperties.getJwtSecret())
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    /**
     * Validate the JWT token.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtConfigProperties.getJwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}