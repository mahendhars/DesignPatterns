package com.prototype.copyconstructor;

class Address {
	public String streetName;
	public String city;
	public String country;

	public Address(String streetName, String city, String country) {
		this.streetName = streetName;
		this.city = city;
		this.country = country;
	}
	
	// Copy Constructor
	public Address(Address other) {
		this(other.streetName, other.city, other.country);
	}

	@Override
	public String toString() {
		return "Address [streetName=" + streetName + ", city=" + city + ", country=" + country + "]";
	}
}

class Employee {
	public String name;
	public Address address;
	
	public Employee(String name, Address address) {
		this.name = name;
		this.address = address;
	}
	
	// Copy Constructor
	public Employee(Employee other) {
		name = other.name;
		address = new Address(other.address);
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", address=" + address + "]";
	}
}

public class PrototypeCopyConstructorDemo {
	
	public static void main(String[] args) {
		
		Address address = new Address("Downing Street", "London", "England");
		Employee employeeJohn = new Employee("John", address);
		
		Employee employeeJane = new Employee(employeeJohn);
		employeeJane.name = "Jane";
		employeeJane.address.streetName = "Up Street";
		
		System.out.println(employeeJohn);
		System.out.println(employeeJane);
	}
}
