package com.work.accounting.models.dtos;

import com.work.accounting.models.entities.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeDTO
{
    protected String name;
    protected String surname;
    protected Double salary;
    protected String password;
    protected Set<Authority> authorities;
    protected DepartmentDTO department;
}
