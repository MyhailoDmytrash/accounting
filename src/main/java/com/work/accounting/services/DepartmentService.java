package com.work.accounting.services;

import com.work.accounting.models.dtos.DepartmentDTO;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public interface DepartmentService
{
    DepartmentDTO create(@NonNull DepartmentDTO departmentDTO);
    Collection<DepartmentDTO> getAll();

    void addEmployee(@NonNull String employeeUUDID, Authentication authentication);

    void removeEmployee(@NonNull String employeeUUDID, Authentication authentication);
}
