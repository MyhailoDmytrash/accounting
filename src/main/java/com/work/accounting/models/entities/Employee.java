package com.work.accounting.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntity implements UserDetails
{
    private String name;
    private String surname;
    private Double salary;
    private String password;
    private String email;

    @ManyToMany(mappedBy = "employees")
    protected Set<Authority> authorities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    protected Department department;

    @PrePersist
    protected void doOnCreate()
    {
        super.doOnCreate();

        salary = 0.0;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
