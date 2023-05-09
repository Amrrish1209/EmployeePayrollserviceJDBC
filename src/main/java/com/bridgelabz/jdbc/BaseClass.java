package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseClass {

	public static Connection connection;
	private static Map<String, PreparedStatement> preparedStatementCache = new HashMap<>();

	public static void main(String[] args) throws SQLException {
		EmployeePayroll employeePayroll = EmployeePayroll.getInstance();
		List<EmployeePayrollData> employeePayrollDataList = employeePayroll.retrieveEmployeePayrollData();
		for (EmployeePayrollData employeePayrollData : employeePayrollDataList) {
			System.out.println(employeePayrollData);
		}

		employeePayroll.updateEmployeePayrollData("Terrisa", 3000000.00);
		employeePayrollDataList = employeePayroll.retrieveEmployeePayrollData();
		for (EmployeePayrollData employeePayrollData : employeePayrollDataList) {
			System.out.println(employeePayrollData);
		}
	}

	public static Connection setUpDatabase() {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
		String username = "root";
		String password = "Pavilion@1209";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is loaded successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IllegalStateException("Cannot load the driver successfully" + e);
		}
		try {
			System.out.println("Databases connected to the database: " + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connection established successfully" + connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static PreparedStatement getPreparedStatement(String query) throws SQLException {
		if (preparedStatementCache.containsKey(query)) {
			return preparedStatementCache.get(query);
		} else {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatementCache.put(query, preparedStatement);
			return preparedStatement;
		}
	}
}
