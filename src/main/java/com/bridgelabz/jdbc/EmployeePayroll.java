package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String gender = resultSet.getString("gender");
			double salary = resultSet.getDouble("salary");
			String start_date = resultSet.getString("start_date");
			String phone = resultSet.getString("phone");
			String address = resultSet.getString("address");
			String department = resultSet.getString("department");
			double basic_pay = resultSet.getInt("basic_pay");
			double deductions = resultSet.getInt("deductions");
			double taxable_pay = resultSet.getInt("taxable_pay");
			double income_tax = resultSet.getInt("income_tax");
			double net_pay = resultSet.getInt("net_pay");

			EmployeePayrollData employeePayrollData = new EmployeePayrollData();
			employeePayrollData.setId(id);
			employeePayrollData.setName(name);
			employeePayrollData.setGender(gender);
			employeePayrollData.setSalary(salary);
			employeePayrollData.setDate(start_date);
			employeePayrollData.setPhone(phone);
			employeePayrollData.setAddress(address);
			employeePayrollData.setDepartment(department);
			employeePayrollData.setBasic_pay(basic_pay);
			employeePayrollData.setDeduction(deductions);
			employeePayrollData.setTaxable_pay(taxable_pay);
			employeePayrollData.setIncome_tax(income_tax);
			employeePayrollData.setNet_pay(net_pay);

			employeePayrollDataList.add(employeePayrollData);
		}

		return employeePayrollDataList;
	}

	public void updateEmployeePayrollData(String name, double basic_pay) throws SQLException {
		String updateQuery = "UPDATE employee_payroll SET basic_pay = ? WHERE name = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		preparedStatement.setFloat(1, (float) basic_pay);
		preparedStatement.setString(2, name);
		int rowsAffected = preparedStatement.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("Record updated successfully");
		} else {
			System.out.println("No records found for the given name");
		}
	}

	public List<EmployeePayrollData> retrieveEmployeePayrollDataByDate(LocalDate startDate, LocalDate endDate)
			throws SQLException {
		Connection connection = BaseClass.setUpDatabase();
		List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
		String query = "SELECT * FROM employee_payroll WHERE start_date BETWEEN ? AND ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setDate(1, Date.valueOf(startDate));
		preparedStatement.setDate(2, Date.valueOf(endDate));
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String gender = resultSet.getString("gender");
			double salary = resultSet.getDouble("salary");
			String start_date = resultSet.getString("start_date");
			String phone = resultSet.getString("phone");
			String address = resultSet.getString("address");
			String department = resultSet.getString("department");
			double basic_pay = resultSet.getInt("basic_pay");
			double deductions = resultSet.getInt("deductions");
			double taxable_pay = resultSet.getInt("taxable_pay");
			double income_tax = resultSet.getInt("income_tax");
			double net_pay = resultSet.getInt("net_pay");

			EmployeePayrollData employeePayrollData = new EmployeePayrollData();
			employeePayrollData.setId(id);
			employeePayrollData.setName(name);
			employeePayrollData.setGender(gender);
			employeePayrollData.setSalary(salary);
			employeePayrollData.setDate(start_date);
			employeePayrollData.setPhone(phone);
			employeePayrollData.setAddress(address);
			employeePayrollData.setDepartment(department);
			employeePayrollData.setBasic_pay(basic_pay);
			employeePayrollData.setDeduction(deductions);
			employeePayrollData.setTaxable_pay(taxable_pay);
			employeePayrollData.setIncome_tax(income_tax);
			employeePayrollData.setNet_pay(net_pay);

			employeePayrollDataList.add(employeePayrollData);
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();

		return employeePayrollDataList;
	}

	public double calculateSumOfSalaryByGender(String gender) throws SQLException {
		String query = "SELECT SUM(salary) FROM employee_payroll WHERE gender = ? GROUP BY gender";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, gender);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getDouble(1);
		}
		return 0.0;
	}

	public double calculateAverageSalaryByGender(String gender) throws SQLException {
		String query = "SELECT AVG(salary) FROM employee_payroll WHERE gender = ? GROUP BY gender";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, gender);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getDouble(1);
		}
		return 0.0;
	}

	public double findMinimumSalaryByGender(String gender) throws SQLException {
		String query = "SELECT MIN(salary) FROM employee_payroll WHERE gender = ? GROUP BY gender";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, gender);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getDouble(1);
		}
		return 0.0;
	}

	public double findMaximumSalaryByGender(String gender) throws SQLException {
		String query = "SELECT MAX(salary) FROM employee_payroll WHERE gender = ? GROUP BY gender";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, gender);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getDouble(1);
		}
		return 0.0;
	}

	public int countEmployeesByGender(String gender) throws SQLException {
		String query = "SELECT COUNT(*) FROM employee_payroll WHERE gender = ? GROUP BY gender";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, gender);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt(1);
		}
		return 0;
	}

	public void insertEmployeePayrollData() throws SQLException {
		// Create a Scanner object to read user input
		Scanner scanner = new Scanner(System.in);

		String insertQuery = "INSERT INTO employee_payroll (name, gender, salary, start_date,phone,address,department,basic_pay,deductions,taxable_pay,income_tax,net_pay) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

		// Get employee details from the user
		System.out.print("Enter employee name: ");
		String name = scanner.nextLine();
		preparedStatement.setString(1, name);

		System.out.print("Enter employee gender: ");
		String gender = scanner.nextLine();
		preparedStatement.setString(2, gender);

		System.out.print("Enter employee salary: ");
		double salary = scanner.nextDouble();
		preparedStatement.setDouble(3, salary);

		System.out.print("Enter employee start date (yyyy-MM-dd): ");
		String startDate = scanner.next();
		preparedStatement.setString(4, startDate);

		scanner.nextLine();

		System.out.println("Enter employee phone: ");
		String phone = scanner.nextLine();
		preparedStatement.setString(5, phone);

		System.out.println("Enter employee address: ");
		String address = scanner.nextLine();
		preparedStatement.setString(6, address);

		System.out.println("Enter employee department: ");
		String department = scanner.nextLine();
		preparedStatement.setString(7, department);

		System.out.println("Enter employee basic_pay: ");
		double basic_pay = scanner.nextDouble();
		preparedStatement.setDouble(8, basic_pay);

		System.out.println("Enter employee deductions: ");
		double deductions = scanner.nextDouble();
		preparedStatement.setDouble(9, deductions);

		System.out.println("Enter employee taxable_pay: ");
		double taxable_pay = scanner.nextDouble();
		preparedStatement.setDouble(10, taxable_pay);

		System.out.println("Enter employee income_tax: ");
		double income_tax = scanner.nextDouble();
		preparedStatement.setDouble(11, income_tax);

		System.out.println("Enter employee net_pay: ");
		double net_pay = scanner.nextDouble();
		preparedStatement.setDouble(12, net_pay);

		// Execute the prepared statement
		preparedStatement.execute();

		System.out.println("Record added successfully");

		// Close the Scanner
		scanner.close();
	}
}