package com.work.accounting.services;

import com.work.accounting.models.dtos.EmployeeDTO;
import com.work.accounting.models.entities.Department;
import com.work.accounting.models.entities.Employee;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

public interface EmployeeService
{
    EmployeeDTO getAllMyData(@NonNull Authentication authentication);

    EmployeeDTO registration(@NonNull EmployeeDTO employeeDTO);

    EmployeeDTO update(@NonNull EmployeeDTO employeeDTO);

    String authentication(@NonNull EmployeeDTO employeeDTO);

    Employee getByAuthenticationData(@NonNull Authentication authentication);

    Employee setDepartmentByEntityUUID(@NonNull String entityUUID, @NonNull Department department);

    Employee removeDepartmentByEntityUUIDAndDepartment(@NonNull String entityUUID, @NonNull Department department);
}
