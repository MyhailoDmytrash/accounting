package com.work.accounting.controller;

import com.work.accounting.models.dtos.DepartmentDTO;
import com.work.accounting.transfers.DepartmentDTOTransfer;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/department")
public interface DepartmentController
{
    @GetMapping("/get-all")
    Collection<DepartmentDTO> getAllDepartments();

    @PostMapping("/create")
    DepartmentDTO createDepartment(@Validated({DepartmentDTOTransfer.Create.class}) @RequestBody DepartmentDTO departmentDTO);

    @PostMapping("/update")
    DepartmentDTO updateDepartment(@RequestBody DepartmentDTO departmentDTO);

    @PostMapping("/employee/add/{employee_uuid}")
    void addEmployee(@PathVariable("employee_uuid") String employeeUUID, Authentication authentication);

    @PostMapping("/employee/remove/{employee_uuid}")
    void removeEmployeeId(@PathVariable("employee_uuid") String employeeUUID, Authentication authentication);
}
