package com.work.accounting.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity
{
    protected String name;
    protected String surname;
    protected Double salary;
    protected String password;
    protected String email;

    @ManyToMany(mappedBy = "employees")
    protected Set<Authority> authorities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    protected Department department;
}
