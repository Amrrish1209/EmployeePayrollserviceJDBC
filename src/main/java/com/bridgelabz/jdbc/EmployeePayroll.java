package com.bridgelabz.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class EmployeePayroll extends BaseClass {

	public void retrieveEmployeePayrollData(LocalDate startDate, LocalDate endDate) throws SQLException {
		connection = setUpDatabase();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT * FROM employee_payroll WHERE start_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)");
		preparedStatement.setDate(1, Date.valueOf(startDate));
		preparedStatement.setDate(2, Date.valueOf(endDate));
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String gender = resultSet.getString(3);
			double salary = resultSet.getDouble(4);
			String date = resultSet.getString(5);
			System.out.println(id + " " + name + " " + gender + " " + salary + " " + date);
		}

		System.out.println("Retrieve employees who joined between " + startDate + " and " + endDate);
	}

	public void updateEmployeePayrollData() throws SQLException {
		connection = setUpDatabase();
		String updateQuery = "update employee_payroll set salary = 3000000.00 WHERE name = 'Terrisa'";
		PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		preparedStatement.executeUpdate();
		System.out.println("Record updated successfully");
	}

	public void analyzeEmployeeDataByGender() throws SQLException {
		connection = setUpDatabase();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT gender, SUM(salary), AVG(salary), MIN(salary), MAX(salary), COUNT(*) "
						+ "FROM employee_payroll " + "GROUP BY gender");

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			String gender = resultSet.getString(1);
			double sumSalary = resultSet.getDouble(2);
			double avgSalary = resultSet.getDouble(3);
			double minSalary = resultSet.getDouble(4);
			double maxSalary = resultSet.getDouble(5);
			int count = resultSet.getInt(6);

			System.out.println("Gender: " + gender);
			System.out.println("Sum Salary: " + sumSalary);
			System.out.println("Average Salary: " + avgSalary);
			System.out.println("Minimum Salary: " + minSalary);
			System.out.println("Maximum Salary: " + maxSalary);
			System.out.println("Count: " + count);
			System.out.println("-------------------------");
		}
	}
}
