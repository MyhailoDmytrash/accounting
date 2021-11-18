package com.work.accounting.controller.impl;

import com.work.accounting.controller.EmployeeController;
import com.work.accounting.models.dtos.EmployeeDTO;
import com.work.accounting.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController
{
    protected final EmployeeService employeeService;

    @Override
    public EmployeeDTO getMyData(Authentication authentication) {
        return null;
    }
}
