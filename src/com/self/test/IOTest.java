package com.self.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.self.io.DBToText;
import com.self.io.TextToDB;

//Invoke com.self.io classes from here
public class IOTest {

	public static void main(String[] args) {

		String fileName = "C:\\Users\\Sushovan\\eclipse-workspace\\Training\\src\\data\\data.txt";

		// Test DBToText
		// read from Database and write to text file

		DBToText readWrite = new DBToText();
		Connection con1 = readWrite.getCon();
		ResultSet rsEmpData = readWrite.readEmp(con1);
		readWrite.writeEm(rsEmpData, fileName);

		// Test TextToDB
		// read from text file and write to database

		TextToDB writeFile = new TextToDB();
		Connection con2 = null;
		ArrayList<String> lines = new ArrayList<String>();
		lines = writeFile.readFromText(fileName);
		con2 = writeFile.getCon(); 
		writeFile.writeToDB(con2, lines);

	}

}
