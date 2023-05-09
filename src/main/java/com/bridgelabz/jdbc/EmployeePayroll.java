package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

		return employeePayrollDataList;
	}

}