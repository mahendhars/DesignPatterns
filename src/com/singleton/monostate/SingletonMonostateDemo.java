package com.singleton.monostate;

class ChiefExecutiveOfficer {
	
	private static String name;
	private static int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		ChiefExecutiveOfficer.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		ChiefExecutiveOfficer.age = age;
	}
	
	@Override
	public String toString() {
		return "ChiefExecutiveOfficer [name=" + name + ", age=" + age + "]";
	}
}

public class SingletonMonostateDemo {
	
	public static void main(String[] args) {
		ChiefExecutiveOfficer ceo1 = new ChiefExecutiveOfficer();
		ceo1.setName("Adam");
		ceo1.setAge(40);
		
		System.out.println(ceo1);
		
		ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();
		// ceo2 state has already been set when setting up ceo2
		System.out.println(ceo2);
	}
}
