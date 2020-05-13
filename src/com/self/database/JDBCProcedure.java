package com.self.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCProcedure {

//connect to database from here
//look at Java API Docs to learn to more
public Connection getCon() {
		
		Connection con = null;
		try {
			// load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded \n");

			// step2 create the connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println("Sussfully Connected \n");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}


//add employee using callable statement 
//callable statement calls to the stored procedure in the database and use set string to set the attributes of the table. 
	public void addEmployeeInfoUsingCallableStmt(Connection con) {
		
		try {
			CallableStatement cstmt = con.prepareCall("{call addEmployee(?,?,?,?,?) }");
			
			cstmt.setInt(1, 1000);
			cstmt.setString(2, "Yukimura");
			cstmt.setString(3, "Sanada");
			cstmt.setString(4, "yuki.sanada@fuji.war");
			
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();
			String result = cstmt.getString(5);
			System.out.println("Result from DB procedure : "+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
