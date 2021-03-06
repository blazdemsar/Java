package com.blazdemsar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.blazdemsar.domain.Address;
import com.blazdemsar.domain.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	JdbcTemplate jdbcTemplate;
	
	public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Employee save(Employee e) {
		String sql = "insert into employee(empId, name, job, salary, addressLine1, addressLine2, city, state) values(?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("Inside of EmployeeDaoImpl.save()...");
		System.out.println("jdbcTemplate: " + jdbcTemplate);
		jdbcTemplate.update(sql, e.getEmpId(), e.getName(), e.getJob(), e.getSalary(), e.getAddress().getAddresssLine1(), e.getAddress().getAddresssLine2(), e.getAddress().getCity(), e.getAddress().getState());
		return e;
	}

	@Override
	public List<Employee> findAll() {
		
		System.out.println("Inside of EmployeeDaoImpl.findAll.....");
		String sql = "select * from employee";
		
		final List<Employee> allEmployees = jdbcTemplate.query(sql, new EmployeeMapper());
		
		return allEmployees;
	}
}

class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee employee = new Employee();
		employee.setEmpId(rs.getInt("empId"));
		employee.setName(rs.getString("name"));
		employee.setJob(rs.getString("job"));
		employee.setSalary(rs.getInt("salary"));
		String addrL1 = rs.getString("addressLine1");
		String addrL2 = rs.getString("addressLine2");
		String city = rs.getString("city");
		String state = rs.getString("state");
		employee.setAddress(new Address(addrL1, addrL2, city, state));
		return employee;
	}
	
}








