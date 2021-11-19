package com.work.accounting.controller.impl;

import com.work.accounting.controller.DepartmentController;
import com.work.accounting.models.dtos.DepartmentDTO;
import com.work.accounting.services.DepartmentService;
import com.work.accounting.transfers.DepartmentDTOTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController
{
    private final DepartmentService departmentService;

    @Override
    public Collection<DepartmentDTO> getAllDepartments() {
        return departmentService.getAll();
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        return departmentService.create(departmentDTO);
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        return null;
    }

    @Override
    public void addEmployee(String employeeUUDID, Authentication authentication)
    {
        departmentService.addEmployee(employeeUUDID, authentication);
    }

    @Override
    public void removeEmployeeId(String employeeUUDID, Authentication authentication)
    {
        departmentService.removeEmployee(employeeUUDID, authentication);
    }
}
