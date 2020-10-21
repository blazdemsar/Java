package com.blazdemsar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blazdemsar.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	  Page<Employee> findAll(Pageable pageable);
	  List<Employee> findAll(Sort sort);
	  
	  
	  List<Employee> findByDesignation(String designation);
	  List<Employee> findBySalary(int salary);
	  List<Employee> findByName(String name);
	  List<Employee> findBySalaryBetween(int minSalary, int maxSalary);
	  
	 @Query(value="select * from employee", nativeQuery=true)
	 List<Employee> findAllTheEmployees();
	 
	 @Query(value="from Employee e where e.salary >=5000")
	 List<Employee> findTheEmployees();
	  
}
