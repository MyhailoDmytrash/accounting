package com.work.accounting.services;

import com.work.accounting.models.entities.Authority;
import com.work.accounting.models.enums.AuthorityEnum;
import lombok.NonNull;

public interface AuthorityService
{
    Authority getByAuthorityEnum(@NonNull AuthorityEnum authorityEnum);
}
