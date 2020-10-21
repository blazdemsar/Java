package com.blazdemsar.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blazdemsar.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	  Page<Employee> findAll(Pageable pageable);
	  List<Employee> findAll(Sort sort);
	  
	  
	  List<Employee> findByJob(String job);
	  List<Employee> findBySalary(int salary);
	  List<Employee> findByName(String name);
	  List<Employee> findBySalaryBetween(int minSalary, int maxSalary);
	  
	 @Query(value="select * from employee", nativeQuery=true)
	 List<Employee> findAllTheEmployees();
	 
	 @Query(value="from Employee e where e.salary >=5000")
	 List<Employee> findTheEmployees();
	 
	 @Transactional //to avoid TransactionRequiredException: Executing an update/delete query
	 @Modifying // Required whenever there is change in database
	 @Query(value="insert into employee(empId, name, designation, salary, insured) "+
	    " values(:empId, :name, :designation, :salary, :insured)", nativeQuery=true)	 
	 public void insertUsingQueryAnnotation(@Param("empId") int empId, @Param("name") String name,
			 @Param("job") String job, @Param("salary") int salary,  @Param("insured") boolean insured);
	 
	 @Transactional
	 @Modifying
	 @Query(value="update Employee e set e.salary=:salary where e.empId=:empId")
	 public void updateSalaryByEmpIdUsingQueryAnnotation(@Param("empId") int empId, @Param("salary") int salary);
	 
	 
	 @Transactional
	 @Modifying
	 @Query(value="delete from Employee e  where e.empId=:empId")
	 public void deleteByIdUsingQueryAnnotation(@Param("empId") int empId);
	 
	 
	 @Query("select e from Employee e where e.empId=:empId")
	 public Employee getById(@Param("empId") int empId);
	  
}
