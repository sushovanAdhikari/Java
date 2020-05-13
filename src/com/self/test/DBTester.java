package com.self.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.self.database.DBConnection;
import com.self.database.Employee;
import com.self.database.JDBCProcedure;

//invoke com.self.database classes from here.
public class DBTester {

	public static void main(String[] args) {

		Connection con = null;
		try {
			DBConnection connection = new DBConnection();
			JDBCProcedure jdbc = new JDBCProcedure();
			
			con = connection.getCon();
			jdbc.addEmployeeInfoUsingCallableStmt(con);

			//jdbc.getEmployeeInfoUsingCallableStmt(con);
			// read data
			//connection.readEmployee(con);

			// update date
			//Employee empUpdate = new Employee(1, "Joseph", "Aocsta", "snyde@gmail.com");
			//connection.updateEmployee(empUpdate, con);

			// create Employee
			//Employee empCreate = new Employee(27, "RetireUp", "Create", "riley@gmail.com");
			//connection.createEmployee(empCreate, con);

			// delete Employee
			//int deleteEmpId = 1;
			//connection.delEmployee(deleteEmpId, con);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
