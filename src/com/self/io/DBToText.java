package com.self.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*Connect to database and write the content of the table from the database to a text file */


public class DBToText {

	//Connect to database from here
	//Look at Java API docs to learn more about the methods
	public Connection getCon() {

		Connection con = null;
		try {
			// load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded in DBToText \n");

			// step2 create the connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println("Sussfully Connected To Oracle \n");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	//get all the datas from the employee table and return it in resultSet data format
	public ResultSet readEmp(Connection con) {
		ResultSet rs = null;
		try {
			String sql = "select * from employees";
			PreparedStatement pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	//write the data received from the employee table to a text file
	public void writeEm(ResultSet rs, String fileName) {
		File file = new File(fileName);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			while (rs.next()) {
				String empId = rs.getString("EMPLOYEE_ID") + "\t";
				String firstName = rs.getString("FIRST_NAME") + "\t";
				String lastName = rs.getString("LAST_NAME") + "\t";
				String email = rs.getString("EMAIL");

				bw.write(empId);
				bw.write(firstName);
				bw.write(lastName);
				bw.write(email);
				bw.newLine();
			}

			bw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fw.close();
				bw.close();
				System.out.println("You have successfully written to the data.txt \n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
