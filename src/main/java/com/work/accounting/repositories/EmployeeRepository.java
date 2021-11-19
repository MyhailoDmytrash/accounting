package com.work.accounting.repositories;

import com.work.accounting.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Optional<Employee> getEmployeeByEmail(String email);
    Optional<Employee> getEmployeeByEntityUUID(String entityUUID);

    Boolean existsEmployeeByEmail(String email);
}
