package com.example.wen.employeetracking;

public class Employee {
	private String firstName;
	private String lastName;
	private String company;
	private int id;
	public Employee(String firstName, String lastName, String company, int id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.id = id;
	}


	public Employee(){
		
	}
	
	public Employee(String firstName, String lastName, String company) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString(){
		return "id: "+id+"\nFirst Name : "+firstName+"\nLast Name: "+lastName+"\nCompany: "+company;
	}

}
