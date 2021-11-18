package com.work.accounting.mappers;

import com.work.accounting.models.dtos.EmployeeDTO;
import com.work.accounting.models.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EmployeeMapper
{
    @Mappings({
            @Mapping(target = "password", ignore = true)
    })
    EmployeeDTO employeeToEmployeeDTO(Employee employee);
}
