package com.blazdemsar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blazdemsar.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	  Page<Employee> findAll(Pageable pageable);
	  List<Employee> findAll(Sort sort);
}
