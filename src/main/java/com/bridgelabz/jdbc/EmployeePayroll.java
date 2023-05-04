package com.bridgelabz.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeePayroll extends BaseClass {

	public void retrieveEmployeePayrollData() throws SQLException {
		connection = setUpDatabase();
		Statement statement = connection.createStatement();
		String query = "select * from employee_payroll";
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String gender = resultSet.getString(3);
			double salary = resultSet.getDouble(4);
			String date = resultSet.getString(5);
			System.out.println(id + " " + name + " " + gender + " " + salary + " " + date);
		}

		System.out.println("Retrieve all the employee payroll data");
	}

	public void updateEmployeePayrollData() throws SQLException {
		connection = setUpDatabase();
		String updateQuery = "update employee_payroll set salary = 3000000.00 WHERE name = 'Terisaa'";
		PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		preparedStatement.executeUpdate();
		System.out.println("Record updated successfully");
	}
}
