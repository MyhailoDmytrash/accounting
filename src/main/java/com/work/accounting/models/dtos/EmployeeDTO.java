package com.work.accounting.models.dtos;

import com.work.accounting.models.entities.Authority;
import com.work.accounting.transfers.EmployeeDTOTransfer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class EmployeeDTO
{
    @NotBlank(groups = {EmployeeDTOTransfer.Registration.class})
    private String name;

    @NotBlank(groups = {EmployeeDTOTransfer.Registration.class})
    private String surname;

    private Double salary;

    @NotBlank(groups = {EmployeeDTOTransfer.Registration.class})
    private String password;

    @NotBlank(groups = {EmployeeDTOTransfer.Registration.class})
    private String email;

    private Set<AuthorityDTO> authorities;

    private DepartmentDTO department;

    private String entityUUID;
    private Date createDate;
}
