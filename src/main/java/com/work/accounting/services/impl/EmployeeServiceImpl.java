package com.work.accounting.services.impl;

import com.work.accounting.exceptions.EmployeeServiceException;
import com.work.accounting.mappers.EmployeeMapper;
import com.work.accounting.models.dtos.EmployeeDTO;
import com.work.accounting.models.entities.Department;
import com.work.accounting.models.entities.Employee;
import com.work.accounting.models.enums.AuthorityEnum;
import com.work.accounting.repositories.EmployeeRepository;
import com.work.accounting.services.AuthorityService;
import com.work.accounting.services.EmployeeService;
import com.work.accounting.services.JWTService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService
{
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthorityService authorityService;

    @Override
    public EmployeeDTO getAllMyData(Authentication authentication)
    {
        return employeeRepository.getEmployeeByEmail(authentication.getName())
                .map(employeeMapper::employeeToEmployeeDTO)
                .orElseThrow(() -> new EmployeeServiceException(EmployeeServiceException.WRONG_AUTHENTICATION_DATA));
    }

    @Override
    public EmployeeDTO registration(EmployeeDTO employeeDTO)
    {
        if(employeeRepository.existsEmployeeByEmail(employeeDTO.getEmail()))
            throw new EmployeeServiceException(EmployeeServiceException.EXISTS_BY_EMAIL);

        return Optional.of(employeeMapper.employeeDTOtoEmployee(employeeDTO))
                .map(employee -> {
                    employee.setPassword(passwordEncoder.encode(employee.getPassword()));
                    employee.setAuthorities(List.of(authorityService.getByAuthorityEnum(AuthorityEnum.EMPLOYEE)));
                    return employee;
                })
                .map(employeeRepository::save)
                .map(employeeMapper::employeeToEmployeeDTO)
                .get();
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO)
    {
        Employee employee = getEmployeeByEntityUUID(employeeDTO.getEntityUUID());

        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());

        return employeeMapper.employeeToEmployeeDTO(employeeRepository.save(employee));
    }

    @Override
    public String authentication(EmployeeDTO employeeDTO) {
        Employee optionalEmployee = employeeRepository.getEmployeeByEmail(employeeDTO.getEmail())
                .filter(employee -> passwordEncoder.matches(employeeDTO.getPassword(), employee.getPassword()))
                .orElseThrow(() -> new EmployeeServiceException(EmployeeServiceException.WRONG_LOGIN_OR_EMAIL));

        return jwtService.generate(optionalEmployee);
    }

    @Override
    public Employee getByAuthenticationData(Authentication authentication) {
        return employeeRepository.getEmployeeByEmail(authentication.getName())
                .orElseThrow(() -> new EmployeeServiceException(EmployeeServiceException.WRONG_AUTHENTICATION_DATA));
    }

    private Employee getEmployeeByEntityUUID(String entityUUID)
    {
        return employeeRepository.getEmployeeByEntityUUID(entityUUID)
                .orElseThrow(() -> new EmployeeServiceException(EmployeeServiceException.WRONG_ENTITY_UUID));
    }

    @Override
    public Employee setDepartmentByEntityUUID(String entityUUID, Department department) {

        Employee employee = getEmployeeByEntityUUID(entityUUID);

        if(employee.getDepartment() != null)
            throw new EmployeeServiceException(EmployeeServiceException.EMPLOYEE_ALREADY_HAS_DEPARTMENT);

        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee removeDepartmentByEntityUUIDAndDepartment(String entityUUID, Department department)
    {
        Employee employee = getEmployeeByEntityUUID(entityUUID);

        if(employee.getDepartment().equals(department))
        {
            employee.setDepartment(null);
            employee = employeeRepository.save(employee);
        }
        else if(!employee.getDepartment().equals(department) && employee.getDepartment() != null)
            throw new EmployeeServiceException(EmployeeServiceException.CAN_NOT_MANIPULATE_WITH_THIS_EMPLOYEE);

        return employee;
    }
}
