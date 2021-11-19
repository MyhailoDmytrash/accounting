package com.work.accounting.models.dtos;

import com.work.accounting.models.entities.Employee;
import com.work.accounting.transfers.DepartmentDTOTransfer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
public class DepartmentDTO
{
    @NotBlank(groups = {DepartmentDTOTransfer.Create.class})
    protected String name;
    protected Employee director;
    protected Set<Employee> substitutes;
    protected Set<Employee> employees;
}
