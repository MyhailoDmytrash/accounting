package com.work.accounting.services.impl;

import com.work.accounting.exceptions.DepartmentServiceException;
import com.work.accounting.mappers.DepartmentMapper;
import com.work.accounting.models.dtos.DepartmentDTO;
import com.work.accounting.models.entities.Department;
import com.work.accounting.models.entities.Employee;
import com.work.accounting.repositories.DepartmentRepository;
import com.work.accounting.services.DepartmentService;
import com.work.accounting.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Directory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService
{
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final EmployeeService employeeService;

    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO)
    {
        if(departmentRepository.existsDepartmentByName(departmentDTO.getName()))
            throw new DepartmentServiceException(DepartmentServiceException.EXISTS_BY_NAME);

        return Optional.of(departmentMapper.departmentDTOtoDepartment(departmentDTO))
                .map(departmentRepository::save)
                .map(departmentMapper::departmentToDepartmentDTO)
                .get();
    }

    @Override
    public Collection<DepartmentDTO> getAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::departmentToDepartmentDTO)
                .collect(Collectors.toList());
    }

    private Department getDepartmentByDirector(Employee employee)
    {
        return departmentRepository.getDepartmentByDirector(employee)
            .orElseThrow(() -> new DepartmentServiceException(DepartmentServiceException.NOT_EXISTS_DEPARTMENT_BY_CURRENT_DIRECTOR));
    }

    private Department getDepartmentByDirectorAuthenticationData(Authentication authentication)
    {
        return getDepartmentByDirector(employeeService.getByAuthenticationData(authentication));
    }

    @Override
    public void addEmployee(String employeeUUDID, Authentication authentication)
    {
        Department department = getDepartmentByDirectorAuthenticationData(authentication);
        employeeService.setDepartmentByEntityUUID(employeeUUDID, department);
    }

    @Override
    public void removeEmployee(String employeeUUDID, Authentication authentication)
    {
        Department department = getDepartmentByDirectorAuthenticationData(authentication);
        employeeService.removeDepartmentByEntityUUIDAndDepartment(employeeUUDID, department);
    }
}
