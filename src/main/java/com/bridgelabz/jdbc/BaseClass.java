package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the start date (yyyy-MM-dd): ");
		String startDateStr = scanner.nextLine();
		LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ISO_DATE);

		System.out.print("Enter the end date (yyyy-MM-dd): ");
		String endDateStr = scanner.nextLine();
		LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ISO_DATE);

		List<EmployeePayrollData> employeesJoinedInRange = employeePayroll.retrieveEmployeePayrollDataByDate(startDate,
				endDate);
		for (EmployeePayrollData employee : employeesJoinedInRange) {
			System.out.println(employee);
		}

		try {
			// Calculate sum of salary by gender
			double sumOfSalaryFemale = employeePayroll.calculateSumOfSalaryByGender("F");
			double sumOfSalaryMale = employeePayroll.calculateSumOfSalaryByGender("M");
			System.out.println("Sum of salary for females: " + sumOfSalaryFemale);
			System.out.println("Sum of salary for males: " + sumOfSalaryMale);

			// Calculate average salary by gender
			double avgSalaryFemale = employeePayroll.calculateAverageSalaryByGender("F");
			double avgSalaryMale = employeePayroll.calculateAverageSalaryByGender("M");
			System.out.println("Average salary for females: " + avgSalaryFemale);
			System.out.println("Average salary for males: " + avgSalaryMale);

			// Find minimum salary by gender
			double minSalaryFemale = employeePayroll.findMinimumSalaryByGender("F");
			double minSalaryMale = employeePayroll.findMinimumSalaryByGender("M");
			System.out.println("Minimum salary for females: " + minSalaryFemale);
			System.out.println("Minimum salary for males: " + minSalaryMale);

			// Find maximum salary by gender
			double maxSalaryFemale = employeePayroll.findMaximumSalaryByGender("F");
			double maxSalaryMale = employeePayroll.findMaximumSalaryByGender("M");
			System.out.println("Maximum salary for females: " + maxSalaryFemale);
			System.out.println("Maximum salary for males: " + maxSalaryMale);

			// Count employees by gender
			int countFemaleEmployees = employeePayroll.countEmployeesByGender("F");
			int countMaleEmployees = employeePayroll.countEmployeesByGender("M");
			System.out.println("Number of female employees: " + countFemaleEmployees);
			System.out.println("Number of male employees: " + countMaleEmployees);
		} catch (SQLException e) {
			e.printStackTrace();
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
