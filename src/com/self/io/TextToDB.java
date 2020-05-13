package com.self.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TextToDB {

	//get connection from database
	public Connection getCon() {

		Connection con = null;
		try {
			// load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded in TextToDB \n");

			// step2 create the connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println("Sussfully Connected To Database\n");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	//returns an array list composed of String line read from the text file 
	public ArrayList<String> readFromText(String fileName) {
		File file = new File(fileName);
		BufferedReader br = null;
		FileReader readFile = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			readFile = new FileReader(file);
			br = new BufferedReader(readFile);

			String s = null;
			while ((s = br.readLine()) != null) {

				lines.add(s);
			}

			return lines;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

	}

	
	//Use the arraylist lines and write to the EMPLOYEE_DATA in the database 
	public void writeToDB(Connection con, ArrayList<String> lines) {

		String sql = "INSERT INTO EMPLOYEE_DATA (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = null;
		String splitString = null;
		String[] words = null;
		int totalRowsAffected = 0;

		for (int i = 0; i < lines.size(); i++) {
			splitString = lines.get(i);
			words = splitString.split("\t");

			try {
				preparedStatement = con.prepareStatement(sql);

				preparedStatement.setInt(1, Integer.parseInt(words[0]));
				preparedStatement.setString(2, words[1]);
				preparedStatement.setString(3, words[2]);
				preparedStatement.setString(4, words[3]);
				
				int rowsAffected = preparedStatement.executeUpdate();
				totalRowsAffected += rowsAffected; 
				System.out.println("ROWS AFFECTED: " + rowsAffected);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}
		}
		
		System.out.println(" \n ******** TOTAL ROWS AFFECTED:\t" + totalRowsAffected + "\t*****************************" );


	}
}
