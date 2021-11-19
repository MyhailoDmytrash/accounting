package com.work.accounting.services.impl;

import com.work.accounting.exceptions.AuthorityServiceException;
import com.work.accounting.models.entities.Authority;
import com.work.accounting.models.enums.AuthorityEnum;
import com.work.accounting.repositories.AuthorityRepository;
import com.work.accounting.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService
{
    private final AuthorityRepository authorityRepository;

    @PostConstruct
    private void init()
    {
        Stream.of(AuthorityEnum.values())
                .map(AuthorityEnum::name)
                .filter(authority -> !authorityRepository.existsAuthorityByAuthority(authority))
                .forEach(authority -> authorityRepository.save(new Authority(authority)));
    }

    @Override
    public Authority getByAuthorityEnum(AuthorityEnum authorityEnum) {
        return authorityRepository.getAuthorityByAuthority(authorityEnum.name())
                .orElseThrow(() -> new AuthorityServiceException(AuthorityServiceException.WRONG_AUTHORITY_NAME));
    }
}
