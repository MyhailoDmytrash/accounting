package com.work.accounting.models.dtos;

import com.work.accounting.models.entities.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class DepartmentDTO
{
    protected String name;
    protected Employee director;
    protected Set<Employee> substitutes;
    protected Set<Employee> employees;
}
