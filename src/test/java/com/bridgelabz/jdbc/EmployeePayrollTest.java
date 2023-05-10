package com.bridgelabz.jdbc;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class EmployeePayrollTest {

	EmployeePayroll employeePayroll;

	@Before
	public void setUp() {
		employeePayroll = EmployeePayroll.getInstance();
	}

	@Test
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() throws SQLException {
		List<EmployeePayrollData> employeePayrollDataList = employeePayroll.retrieveEmployeePayrollData();
		Assertions.assertEquals(7, employeePayrollDataList.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDatabase() throws SQLException {
		employeePayroll.updateEmployeePayrollData("Terisaa", 3000000.00);
		List<EmployeePayrollData> employeePayrollDataList = employeePayroll.retrieveEmployeePayrollData();
		double updatedSalary = 0.0;

		for (EmployeePayrollData employee : employeePayrollDataList) {
			if (employee.getName().equals("Terisaa")) {
				updatedSalary = employee.getSalary();
				break;
			}
		}

		Assertions.assertEquals(3000000.00, updatedSalary);
	}

	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() throws SQLException {
		LocalDate startDate = LocalDate.of(2022, 01, 01);
		LocalDate endDate = LocalDate.now();
		List<EmployeePayrollData> employeePayrollDataList = employeePayroll.retrieveEmployeePayrollDataByDate(startDate,
				endDate);
		Assertions.assertEquals(3, employeePayrollDataList.size());
	}

	@Test
	public void givenPayrollData_WhenAverageSalaryRetrievedByGender_ShouldReturnProperValue() throws SQLException {
		double avgSalaryFemale = employeePayroll.calculateAverageSalaryByGender("F");
		double avgSalaryMale = employeePayroll.calculateAverageSalaryByGender("M");
		Assertions.assertEquals(1166766.6666666667, avgSalaryFemale);
		Assertions.assertEquals(788625.0, avgSalaryMale);
	}

	@Test
	public void givenPayrollData_WhenSumOfSalaryRetrievedByGender_ShouldReturnProperValue() throws SQLException {
		double sumOfSalaryFemale = employeePayroll.calculateSumOfSalaryByGender("F");
		double sumOfSalaryMale = employeePayroll.calculateSumOfSalaryByGender("M");
		Assertions.assertEquals(3500300.0, sumOfSalaryFemale);
		Assertions.assertEquals(3154500.0, sumOfSalaryMale);
	}

	@Test
	public void givenPayrollData_WhenMinimumSalaryRetrievedByGender_ShouldReturnProperValue() throws SQLException {
		double minSalaryFemale = employeePayroll.findMinimumSalaryByGender("F");
		double minSalaryMale = employeePayroll.findMinimumSalaryByGender("M");
		Assertions.assertEquals(300.00, minSalaryFemale);
		Assertions.assertEquals(4500.00, minSalaryMale);
	}

	@Test
	public void givenPayrollData_WhenMaximumSalaryRetrievedByGender_ShouldReturnProperValue() throws SQLException {
		double maxSalaryFemale = employeePayroll.findMaximumSalaryByGender("F");
		double maxSalaryMale = employeePayroll.findMaximumSalaryByGender("M");
		Assertions.assertEquals(3000000.00, maxSalaryFemale);
		Assertions.assertEquals(3000000.00, maxSalaryMale);
	}

	@Test
	public void givenPayrollData_WhenCountByGender_ShouldReturnProperValue() throws SQLException {
		int countFemale = employeePayroll.countEmployeesByGender("F");
		int countMale = employeePayroll.countEmployeesByGender("M");
		Assertions.assertEquals(3, countFemale);
		Assertions.assertEquals(4, countMale);
	}
}
