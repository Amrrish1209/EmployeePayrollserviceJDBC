package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayroll {
	private static EmployeePayroll instance;
	private static Connection connection;

	private EmployeePayroll() {
		connection = BaseClass.setUpDatabase();
	}

	public static EmployeePayroll getInstance() {
		if (instance == null) {
			instance = new EmployeePayroll();
		}
		return instance;
	}

	public List<EmployeePayrollData> retrieveEmployeePayrollData() throws SQLException {
		List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
		String query = "SELECT * FROM employee_payroll";
		PreparedStatement preparedStatement = BaseClass.getPreparedStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String gender = resultSet.getString(3);
			double salary = resultSet.getDouble(4);
			String date = resultSet.getString(5);

			EmployeePayrollData employeePayrollData = new EmployeePayrollData(id, name, gender, salary, date);
			employeePayrollDataList.add(employeePayrollData);
		}

		return employeePayrollDataList;
	}

	public void updateEmployeePayrollData(String name, double salary) throws SQLException {
		String updateQuery = "UPDATE employee_payroll SET salary = ? WHERE name = ?";
		PreparedStatement preparedStatement = BaseClass.getPreparedStatement(updateQuery);
		preparedStatement.setDouble(1, salary);
		preparedStatement.setString(2, name);
		preparedStatement.executeUpdate();
		System.out.println("Record updated successfully");
	}
}