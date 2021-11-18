package com.work.accounting.controller;

import com.work.accounting.models.dtos.EmployeeDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/employee")
public interface EmployeeController
{
    @GetMapping("/get-my-data")
    EmployeeDTO getMyData(Authentication authentication);
}
