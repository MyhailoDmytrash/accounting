package com.work.accounting.mappers;

import com.work.accounting.models.dtos.EmployeeDTO;
import com.work.accounting.models.entities.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper
{
    @Mappings({
            @Mapping(target = "password", ignore = true),
    })
    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "name", source = "employeeDTO.name"),
            @Mapping(target = "surname", source = "employeeDTO.surname"),
            @Mapping(target = "email", source = "employeeDTO.email"),
            @Mapping(target = "password", source = "employeeDTO.password")
    })
    Employee employeeDTOtoEmployee(EmployeeDTO employeeDTO);
}
