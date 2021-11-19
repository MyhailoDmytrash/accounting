package com.work.accounting.controller;

import com.work.accounting.models.dtos.EmployeeDTO;
import com.work.accounting.transfers.EmployeeDTOTransfer;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/employee")
public interface EmployeeController
{
    @GetMapping("/get-my-data")
    EmployeeDTO getMyData(Authentication authentication);

    @PostMapping("/register")
    EmployeeDTO register(@Validated({EmployeeDTOTransfer.Registration.class}) @RequestBody EmployeeDTO employeeDTO);

    @PostMapping("/update")
    EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO);

    @PostMapping("/authentication")
    String authentication(@RequestBody EmployeeDTO employeeDTO);
}
