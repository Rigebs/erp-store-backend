package com.rige.security;


import com.rige.entities.RoleEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final UserDetailServiceImpl userDetailsService;

    String jwtSecret;
    long jwtExpiration;

    @Value("${jwt.secret}")
    public void setJwtSecret(String secret) {
        jwtSecret = secret;
    }

    @Value("${jwt.expiration}")
    public void setJwtExpiration(long expiration) {
        jwtExpiration = expiration;
    }

    public String createToken(String username, String email, Set<RoleEntity> roles) {
        System.out.println("SE CREA-");
        long expirationTime = jwtExpiration * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("username", username);

        Set<String> roleNames = new HashSet<>();
        for (RoleEntity role : roles) {
            roleNames.add(role.getName());
        }
        extra.put("roles", roleNames);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            return new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
        } catch (JwtException e) {
            System.out.println("EXCEP: " + e);
            return null;
        }
    }
}