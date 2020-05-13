package com.self.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*Learn to connect to database and make calls to the database using prepared statement.
Main methods invoking the class can be found >com.self.test> DBTester*/

public class DBConnection {

	
	//Connect to database from here
	//Look at Java API docs to learn more about the methods
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

	// read Employee using perpared statement
	public void readEmployee(Connection con) {
		String sql = "select * from employees";
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String empId = rs.getString("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastNmae = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				System.out.println(empId + " " + firstName + " " + lastNmae + email + "\n");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// update Employee using prepared statement 
	public void updateEmployee(Employee employee, Connection con) {
		System.out.println("EMPLOYEE NAME " + employee.getFirstName());
		String sql = "UPDATE EMPLOYEES SET FIRST_NAME=? , LAST_NAME=? where EMPLOYEE_ID=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setInt(3, employee.getId());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("ROWS AFFECTED: " + rowsAffected);
		} catch (SQLException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	}

	// create Employee using prepared statement
	public void createEmployee(Employee employee, Connection con) {
		System.out.println("EMPLOYEE NAME " + employee.getFirstName());
		String sql = "INSERT INTO EMPLOYEEs (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, getEmail()) VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);

			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getEmail());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("ROWS AFFECTED: " + rowsAffected);
		} catch (SQLException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	}

	// delete employee using prepared statement 
	public void delEmployee(int empId, Connection con) {

		String sql = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);

			preparedStatement.setInt(1, empId);

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("ROWS AFFECTED: " + rowsAffected);
		} catch (SQLException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	}

}
