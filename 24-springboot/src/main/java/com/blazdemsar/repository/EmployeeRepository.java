package com.blazdemsar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazdemsar.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
