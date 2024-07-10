package com.example.shiftmanagement.repository;

import com.example.shiftmanagement.model.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeShiftRepository extends JpaRepository<EmployeeShift, Long> {
    
    @Query("SELECT DISTINCT e.employee.employeeName FROM EmployeeShift e WHERE LOWER(e.employee.employeeName) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<String> findByEmployeeName(String employeeName);

    List<EmployeeShift> findByMonthAndYear(String month, int year);

    List<EmployeeShift> findByMonthAndYearAndEmployee_EmployeeName(String month, int year, String employeeName);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM employee_sunday_shifts WHERE employee_shift_id IN (SELECT id FROM employee_shift WHERE month = ?1 AND year = ?2)", nativeQuery = true)
    void deleteSundayShiftsByMonthAndYear(String month, int year);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM employee_shift WHERE month = ?1 AND year = ?2", nativeQuery = true)
    void deleteByMonthAndYear(String month, int year);
    
    List<EmployeeShift> findByEmployee_EmployeeNameIgnoreCase(String employeeName);
}
