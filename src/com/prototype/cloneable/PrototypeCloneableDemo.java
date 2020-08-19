package com.prototype.cloneable;

import java.util.Arrays;

// Cloneable is a marker interface
class Address implements Cloneable {
	public String streetName;
	public int doorNumber;
	
	public Address(String streetName, int doorNumber) {
		this.streetName = streetName;
		this.doorNumber = doorNumber;
	}
	
	@Override
	public String toString() {
		return "Address [streetName=" + streetName + ", doorNumber=" + doorNumber + "]";
	}
	
	// base class clone() is protected
	@Override
	public Object clone() {
		return new Address(streetName, doorNumber);
	}
}

class Person implements Cloneable {
	public String[] names;
	public Address address;
	
	public Person(String[] names, Address address) {
		this.names = names;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + Arrays.toString(names) + ", address=" + address + "]";
	}
	
	@Override
	public Object clone() {
		return new Person(names.clone(), (Address) address.clone());
	}
}

public class PrototypeCloneableDemo {
	
	public static void main(String[] args) {
		Address address = new Address("Downing Streets", 123);
		Person john = new Person(new String[]{"John", "Smith"}, address);
		
		Person jane = (Person) john.clone();
		jane.names[0] = "Jane";
		jane.address.doorNumber = 124;
		
		System.out.println(john);
		System.out.println(jane);
	}
}
