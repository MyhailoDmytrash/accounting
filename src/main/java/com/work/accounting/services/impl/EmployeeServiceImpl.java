package com.work.accounting.services.impl;

import com.work.accounting.exceptions.EmployeeServiceException;
import com.work.accounting.mappers.EmployeeMapper;
import com.work.accounting.models.dtos.EmployeeDTO;
import com.work.accounting.repositories.EmployeeRepository;
import com.work.accounting.services.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService
{
    protected final EmployeeRepository employeeRepository;
    protected final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO getAllMyData(@NonNull Authentication authentication)
    {
        return employeeRepository.getEmployeeByEmail(authentication.getName())
                .map(employeeMapper::employeeToEmployeeDTO)
                .orElseThrow(() -> new EmployeeServiceException(EmployeeServiceException.WRONG_EMAIL));
    }
}
