package com.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class User {
	String fullName;

	public User(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "User [fullName=" + fullName + "]";
	}
}

class User2 {
	static List<String> strings = new ArrayList<String>();
	int[] names;
	
	public User2(String name) {
		Function<String, Integer> getOrAdd = (String s) -> {
			int index = strings.indexOf(s);
			if(index != -1) 
				return index;
			else {
				strings.add(s);
				return strings.size()-1;
			}
		};
		
		names = Arrays.stream(name.split(" ")).mapToInt(s -> getOrAdd.apply(s)).toArray();
	}
	
	public String getFullName() {
		return Arrays.stream(names).mapToObj(index -> strings.get(index)).collect(Collectors.joining(", "));
	}
}

public class FlyweightDemo1 {
	public static void main(String[] args) {
		
		// here the fullNames consist of repetitive lastnames
		// We can optimize on space by saving a single instance of the lastname and use pointer of it
		User user1 = new User("John Smith");
		User user2 = new User("Jane Smith");
		
		User2 user21 = new User2("John Smith");
		User2 user22 = new User2("Jane Smith");
		
		System.out.println("User2 strings : " + User2.strings);
		System.out.println("user21 names : " + Arrays.toString(user21.names));
		System.out.println(user21.getFullName());
		System.out.println("user22 names : " + Arrays.toString(user22.names));
		System.out.println(user22.getFullName());
	}
}
