package com.blazdemsar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.blazdemsar.domain.Address;
import com.blazdemsar.domain.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Employee save(Employee employee) {
		System.out.println("Inside of EmployeeDaoImpl.save()....");
		String sql = "insert into employee(empId, name, job, salary, addressLine1, addressLine2, city, state) values(?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, employee.getEmpId(), employee.getName(), employee.getJob(), employee.getSalary(), employee.getAddress().getAddressLine1(), employee.getAddress().getAddressLine2(), employee.getAddress().getCity(), employee.getAddress().getState());
		return employee;
	}

	@Override
	public List<Employee> findAll() {
		System.out.println("Inside of EmployeeDaoImpl.findAll()....");
		String sql = "select * from employee";
		final List<Employee> allEmployees = jdbcTemplate.query(sql, new EmployeeMapper());
		return allEmployees;
	}

	@Override
	public Employee findById(int empId) {
		System.out.println("Inside of EmployeeDaoImpl.findById()....");
		String sql = "select * from employee where empId=" + empId;
		Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeMapper());
		return employee;
	}

	@Override
	public Employee updateById(int empId, String job, int salary) {
		System.out.println("Inside of EmployeeDaoImpl.updateById()....");
		String sql = "update employee set job=? salary=? where empId=?";
		jdbcTemplate.update(sql, new Object[] {job, salary, empId});
		System.out.println("Employee with Emp ID = " + empId + " has been successfully updated!");
		return null;
	}

	@Override
	public void deleteById(int empId) {
		System.out.println("Inside of EmployeeDaoImpl.deleteById()....");
		String sql = "delete from employee where empId=?";
		jdbcTemplate.update(sql, new Object[] {empId});
		System.out.println("Employee with Emp ID = " + empId + " has been deleted!");
	}

}

class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee e = new Employee();
		e.setEmpId(rs.getInt("empId"));
		e.setName(rs.getString("name"));
		e.setJob(rs.getString("job"));
		e.setSalary(rs.getInt("salary"));
		String adrL1 = rs.getString("addressLine1");
		String adrL2 = rs.getString("addressLine2");
		String city = rs.getString("city");
		String state = rs.getString("state");
		e.setAddress(new Address(adrL1, adrL2, city, state));
		
		return e;
	}
	
}





