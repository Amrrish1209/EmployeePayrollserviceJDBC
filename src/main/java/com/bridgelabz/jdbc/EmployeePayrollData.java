package com.bridgelabz.jdbc;

public class EmployeePayrollData {

	private int id;
	private String name;
	private String gender;
	private double salary;
	private String date;

	public EmployeePayrollData(int id, String name, String gender, double salary, String date) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.salary = salary;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "EmployeePayrollData{" + "id=" + id + ", name='" + name + '\'' + ", gender='" + gender + '\''
				+ ", salary=" + salary + ", date='" + date + '\'' + '}';
	}
}
