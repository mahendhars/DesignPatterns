package com.builder.faceted;

class Person {
	// address
	String streetAddress, postCode, city;
	
	// employment
	String company, position;
	int annualIncome;
	
	@Override
	public String toString() {
		return "Person [streetAdress=" + streetAddress + ", postCode=" + postCode + ", city=" + city + ", company="
				+ company + ", position=" + position + ", annualIncome=" + annualIncome + "]";
	}
}

// facade builder
class PersonBuilder {
	
	// the object we are going to build
	Person person = new Person();
	
	public PersonAddressBuilder lives() {
		PersonAddressBuilder personAddressBuilder = new PersonAddressBuilder(person);
		return personAddressBuilder;
	}
	
	public PersonJobBuilder works() {
		PersonJobBuilder personJobBuilder = new PersonJobBuilder(person);
		return personJobBuilder;
	}
	
	Person build() {
		return person;
	}
}

class PersonAddressBuilder extends PersonBuilder {
	
	public PersonAddressBuilder(Person person) {
		this.person = person;
	}
	
	public PersonAddressBuilder at(String streetAddress) {
		this.person.streetAddress = streetAddress;
		return this;
	}
	
	public PersonAddressBuilder withPostCode(String postCode) {
		this.person.postCode = postCode;
		return this;
	}
	
	public PersonAddressBuilder in(String city) {
		this.person.city = city;
		return this;
	}
}

class PersonJobBuilder extends PersonBuilder {
	
	public PersonJobBuilder(Person person) {
		this.person = person;
	}
	
	public PersonJobBuilder at(String company) {
		this.person.company = company;
		return this;
	}
	
	public PersonJobBuilder asA(String position) {
		this.person.position = position;
		return this;
	}
	
	public PersonJobBuilder earning(int annualIncome) {
		this.person.annualIncome = annualIncome;
		return this;
	}
}

public class BuilderFacetedDemo {

	public static void main(String[] args) {
		
		Person person = new PersonBuilder()
				.lives()
					.at("Street 10")
					.withPostCode("500081")
					.in("Hyderabad")
				.works()
					.at("Verizon")
					.asA("Developer")
					.earning(2500000)
				.build();
		
		System.out.println(person);
	}
}
