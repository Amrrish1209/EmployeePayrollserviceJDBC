package com.bridgelabz.jdbc;

public class EmployeePayrollData {

	private int id;
	private String name;
	private String gender;
	private double salary;
	private String date;
	private String phone;
	private String address;
	private String department;
	private double basic_pay;
	private double deductions;
	private double taxable_pay;
	private double income_tax;
	private double net_pay;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getBasic_pay() {
		return basic_pay;
	}

	public void setBasic_pay(double basic_pay) {
		this.basic_pay = basic_pay;
	}

	public double getDeduction() {
		return deductions;
	}

	public void setDeduction(double deductions) {
		this.deductions = deductions;
	}

	public double getTaxable_pay() {
		return taxable_pay;
	}

	public void setTaxable_pay(double taxable_pay) {
		this.taxable_pay = taxable_pay;
	}

	public double getIncome_tax() {
		return income_tax;
	}

	public void setIncome_tax(double income_tax) {
		this.income_tax = income_tax;
	}

	public double getNet_pay() {
		return net_pay;
	}

	public void setNet_pay(double net_pay) {
		this.net_pay = net_pay;
	}

	@Override
	public String toString() {
		return "EmployeePayrollData{" + "id=" + id + ", name='" + name + '\'' + ", gender='" + gender + '\''
				+ ", salary=" + salary + ", date='" + date + '\'' + ", phone=" + phone + ", address='" + address + '\''
				+ ", department='" + department + '\'' + ", basic_pay=" + basic_pay + ", deductions=" + deductions
				+ ", taxable_pay=" + taxable_pay + ", income_tax=" + income_tax + ", net_pay=" + net_pay + '}';
	}

}
