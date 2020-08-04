package com.blazdemsar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blazdemsar.domain.Employee;

// DAO : Data Access Object
public class EmployeeDao {
	
	private String url = "jdbc:mysql://localhost:3306/blaz_db";
	private String username = "root";
	private String password = "password";
	Connection connection = null;
	
	public EmployeeDao() {
		
	}
	
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {				
				e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public List<Employee> allEmployees() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Employee> empList = new ArrayList<>();
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement("select * from employee");
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int empId = rs.getInt("empId");
				String name = rs.getString("name");
				String job = rs.getString("job");
				int salary = rs.getInt("salary");
				
				empList.add(new Employee(empId, name, job, salary)); 
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (preparedStatement != null) {
				
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null) {
				
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return empList;
	}
	
	public void saveEmployee(Employee employee) throws SQLException {
		String sql = "insert into employee(name, job, salary) values(?, ?, ?);";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getJob());
			preparedStatement.setInt(3, employee.getSalary());
			
			preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			if (preparedStatement != null) {
				
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null) {
				
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean deleteEmployee(int empId) throws SQLException {
		String sql = "delete from employee where empId=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean deleted = false;
		
		try {
			
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, empId);
			
			deleted = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	public Employee getEmployee(int empId) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "select * from employee where empId=?";
		Employee employee = null;
		
		try {
			
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, empId);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				empId = rs.getInt("empId");
				String name = rs.getString("name");
				String job = rs.getString("job");
				int salary = rs.getInt("salary");
				
				employee = new Employee(empId, name, job, salary);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}
	
	public boolean updateEmployee(Employee employee) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "update employee set name=?, job=?, salary=? where empId=?";
		boolean updated = false;
		
		try {
			
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getJob());
			preparedStatement.setInt(3, employee.getSalary());
			preparedStatement.setInt(4, employee.getEmpId());
			
			updated = preparedStatement.executeUpdate() > 0;
						
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updated;
	}
	
	public List<Employee> searchEmployee(int empId, String name, String job, int salary) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "select * from employee where empId=? and name like ? and designation like ? and salary=?";
		List<Employee> empList = new ArrayList<>();
		
		try {
			
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, empId);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, job);
			preparedStatement.setInt(4, salary);
			
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				empId = rs.getInt("empId");
				name = rs.getString("name");
				job = rs.getString("job");
				salary = rs.getInt("salary");
				
				empList.add(new Employee(empId, name, job, salary));
				
			}
						
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empList;
	}
}
