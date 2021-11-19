package com.work.accounting.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@ToString(of = {"authority"})
@NoArgsConstructor
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

    public Authority(String authority)
    {
        this.authority = authority;
    }
}
