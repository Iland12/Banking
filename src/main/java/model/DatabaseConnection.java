package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	
	private static final String URL="jdbc:mysql://localhost:3308/banking";
	private static final String USER="root";
	private static final String PASS="route";
	
	public static Connection con() {
		
		Connection conn=null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(URL, USER, PASS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return conn;

		
	}
	
}