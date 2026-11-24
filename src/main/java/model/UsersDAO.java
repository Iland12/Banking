package model;

import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;


public class UsersDAO {

	
	public boolean checkEmail(String email) {

		try {
			
			Connection con= DatabaseConnection.con();
			
			String sql="SELECT * FROM users WHERE email=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, email);
			
			ResultSet rs= ps.executeQuery();
			
			boolean result= rs.next();
			
			
			con.close();
			
			return result;
			
			
		} catch (Exception e) {
		  
			e.printStackTrace();
		}
		return false;

	}	
	
	
	
	
	// Register Users
	
	public boolean  register(Users user) {
		
		try {
			Connection con= DatabaseConnection.con();
			
			String hashPassword =BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12) );
			
			String sql ="""
					INSERT INTO users(user_name,user_email,user_phone,user_password)
					VALUES(?,?,?,?)
					""";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhone());
			ps.setString(4, hashPassword);
			
			int result= ps.executeUpdate();
				con.close();
				
			return result >0;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
		
	}
	
	
}