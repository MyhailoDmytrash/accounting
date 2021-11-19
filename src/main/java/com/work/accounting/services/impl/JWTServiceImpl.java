package com.work.accounting.services.impl;

import com.work.accounting.models.entities.Authority;
import com.work.accounting.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService
{
    @Value("${spring.security.jwt.key}")
    private String tokenFromProperties;

    private Key key;

    @PostConstruct
    private void init()
    {
        key = Keys.hmacShaKeyFor(tokenFromProperties.getBytes());
    }

    @Override
    public String generate(UserDetails userDetails) {
        return Jwts.builder().setClaims(Map.of(
                "email", userDetails.getUsername(),
                "password", userDetails.getPassword(),
                "auth", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                    ))
                .signWith(key)
                .compact();
    }

    @Override
    public Optional<Authentication> parse(String jwt) {
        try
        {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt).getBody();

            return Optional.of(new UsernamePasswordAuthenticationToken(
                    claims.get("email"),
                    claims.get("password"),
                    ((List<String>)claims.get("auth")).stream().map(Authority::new).collect(Collectors.toList())
            ));
        }
        catch (Throwable exception)
        {
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }
}
