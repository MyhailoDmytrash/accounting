package com.work.accounting.services;

import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface JWTService
{
    String generate(@NonNull UserDetails userDetails);
    Optional<Authentication> parse(String jwt);
}
