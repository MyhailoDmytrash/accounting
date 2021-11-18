package com.work.accounting.services;

import com.work.accounting.models.dtos.EmployeeDTO;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

public interface EmployeeService
{
    EmployeeDTO getAllMyData(@NonNull Authentication authentication);
}
