package com.blazdemsar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.blazdemsar.domain.Employee;
import com.blazdemsar.domain.Address;

public class EmployeeDaoImplJdbcTemplate implements EmployeeDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		String sql = "insert into employee(name, job, salary, addressLine1, addressLine2, city, state) values('"+
				employee.getName() + "', '" +
				employee.getJob() + "', " +
				employee.getSalary() + ", '" +
				employee.getAddress().getAddressLine1() + "', '" +
				employee.getAddress().getAddressLine2() + "', '" +
				employee.getAddress().getCity() + "', '" +
				employee.getAddress().getState() + "')";
		
		System.out.println("SQL statement : " + sql);
		jdbcTemplate.execute(sql);
		
		return employee;
	}

	@Override
	public List<Employee> findAll() {
		String sql = "select * from employee";
		System.out.println("SQL stmt : " + sql);
		List<Employee> allEmployees = jdbcTemplate.query(sql, new ResultSetExtractor<List<Employee>>() {

			@Override
			public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Employee> allEmployees = new ArrayList<>();
				
				while(rs.next()) {
					Employee e = new Employee();
					e.setEmpId(rs.getInt("empId"));
					e.setName(rs.getString("name"));
					e.setJob(rs.getString("job"));
					e.setSalary(rs.getInt("salary"));
					String addressLine1 = rs.getString("addressLine1");
					String addressLine2 = rs.getString("addressLine2");
					String city = rs.getString("city");
					String state = rs.getString("state");
					e.setAddress(new Address(addressLine1, addressLine2, city, state));
					System.out.println(e);
					allEmployees.add(e);
				}
				
				return allEmployees;
			}});
		
		return allEmployees;
	}

	@Override
	public void deleteById(int empId) {
		String sql = "delete from employee where empId=?";
		jdbcTemplate.update(sql, new Object[] {empId});
		System.out.println("Deleted employee record with EMPID : " + empId);
		
	}

	@Override
	public Employee findById(int empId) {
		String sql="select * from employee where empId=" + empId;
		Employee employee = jdbcTemplate.queryForObject(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee e = new Employee();
				e.setEmpId(rs.getInt("empId"));
				e.setName(rs.getString("name"));
				e.setJob(rs.getString("job"));
				e.setSalary(rs.getInt("salary"));
				String addressLine1 = rs.getString("addressLine1");
				String addressLine2 = rs.getString("addressLine2");
				String city = rs.getString("city");
				String state = rs.getString("state");
				e.setAddress(new Address(addressLine1, addressLine2, city, state));
				System.out.println("Employee with EMPID " + empId + "found : " + e);
				return e;
			}});
		
		return employee;
	}

	@Override
	public Employee updateById(int empId, String job, int salary) {
		String sql = "update employee set job=?, salary=? where empId=?";
		jdbcTemplate.update(sql, new Object[] {job, salary, empId});
		System.out.println("Employee with EMPID = " + empId + " was successfully updated!");
		return null;
	}
}
