package com.builder;

public class BuilderFluentRecursiveGenericsDemo {
	
	public static void main(String[] args) {
		EmployeeBuilder pb = new EmployeeBuilder();
		Person person = pb.withName("Mike").withPostion("Developer").build();
		System.out.println(person);
	}
}

class Person {
	public String name;
	public String position;
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", position=" + position + "]";
	}
}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {
	protected Person person = new Person();
	
	public SELF withName(String name) {
		person.name = name;
		return self();
	}
	
	public Person build() {
		return person;
	}
	
	public SELF self() {
		return (SELF) this;
	}
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {
	
	public EmployeeBuilder withPostion(String position) {
		person.position = position;
		return self();
	}
	
	@Override
	public EmployeeBuilder self() {
		return this;
	}
}
