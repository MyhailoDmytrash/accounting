package com.work.accounting.mappers;

import com.work.accounting.models.dtos.DepartmentDTO;
import com.work.accounting.models.entities.Department;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {EmployeeMapper.class})
public interface DepartmentMapper
{
    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "name", source = "departmentDTO.name")
    })
    Department departmentDTOtoDepartment(DepartmentDTO departmentDTO);

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "name", source = "department.name"),
            @Mapping(target = "director", source = "department.director"),
            @Mapping(target = "substitutes", source = "department.substitutes"),
            @Mapping(target = "employees", source = "department.employees")
    })
    DepartmentDTO departmentToDepartmentDTO(Department department);

}
