package com.synergisticit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.synergisticit.domain.User;

public class UserDao {
	static ResultSet rs;
	
	public static User login(User user, Connection connection) {
		Statement stmt = null;
		String username = user.getUsername();
		String password = user.getPassword();
		
		String registeredusername = null;
		String registerdpassword = null;
		String sql = "select * from user where username='"+username +"' and password='"+password +"'";
		System.out.println("sql: "+sql);
		System.out.println("username:"+username + ", passowrd:"+password);
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			boolean existsUser = rs.next();
			if(!existsUser) {
				System.out.println("You are not a registered User....");
			}else if(existsUser) {
				
				registeredusername = rs.getString("username");
				registerdpassword = rs.getString("password");
				String email =rs.getString("email");
				boolean valid = rs.getBoolean("valid");
				
				System.out.println("registeredusername:"+ registeredusername + ", registerdpassword:"
				+registerdpassword + ", email:" +email +", valid:"+valid);
				user.setUsername(registeredusername);
				user.setPassword(registerdpassword);
				user.setEmail(email);
				user.setValid(valid);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}// end of Login(user, connection)
	
	public static void registerTheUser(String name, String password, String email, boolean valid, Connection con) {
		String sql = "insert into user(username, password, email, valid) values(?, ?, ?, ?)";
		System.out.println("sql: "+sql);
		try(PreparedStatement preparedStatement = con.prepareStatement(sql);){
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			
			preparedStatement.setBoolean(4, valid);
			
			preparedStatement.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
