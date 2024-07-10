package com.example.shiftmanagement.repository;

import com.example.shiftmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeNameIgnoreCase(String employeeName);
}
