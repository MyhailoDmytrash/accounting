package com.work.accounting.repositories;

import com.work.accounting.models.entities.Department;
import com.work.accounting.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>
{
    Optional<Department> getDepartmentByDirector(Employee director);
    Boolean existsDepartmentByName(String name);
}
