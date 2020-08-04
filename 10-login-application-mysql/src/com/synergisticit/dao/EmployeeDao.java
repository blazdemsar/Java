package com.synergisticit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.synergisticit.domain.Employee;

public class EmployeeDao {
	static ResultSet rs;
	
	public List<Employee> allEmployees(Connection connection) {
		
		rs = null;
		List<Employee> empList = new ArrayList<>();
		PreparedStatement sql = null;
		
		try {

			sql = connection.prepareStatement("select * from employee");
			rs = sql.executeQuery();
			
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
			
			if (sql != null) {
				
				try {
					sql.close();
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
}
