package com.work.accounting.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(of = { "authority" })
public class Authority implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    protected String authority;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "authority_employee",
            joinColumns = @JoinColumn(name = "authority_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    protected Set<Employee> employees;
}
