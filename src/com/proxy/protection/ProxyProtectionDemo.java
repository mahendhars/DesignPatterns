package com.proxy.protection;

interface Drivable {
	void drive();
}

class Driver {
	
	int age;

	public Driver(int age) {
		this.age = age;
	}
}

class Car implements Drivable {
	
	protected Driver driver;

	public Car(Driver driver) {
		this.driver = driver;
	}
	
	public void drive() {
		System.out.println("Driver of age " + driver.age + " driving the car");
	}
}

class CarProxy extends Car {

	public CarProxy(Driver driver) {
		super(driver);
	}

	@Override
	public void drive() {
		if (driver.age < 18) {
			System.out.println("Driver of age " + driver.age + " is too young to Drive");
		} else {
			super.drive();
		}
	}
}

public class ProxyProtectionDemo {
	public static void main(String[] args) {
		Driver driver = new Driver(12);
		
		Car car1 = new Car(driver);
		car1.drive();
		
		Car car2 = new CarProxy(driver);
		car2.drive();
	}
}
