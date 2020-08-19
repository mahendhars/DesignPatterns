package com.singleton.enumbased;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

enum EnumSingleton {
	INSTANCE;

	// enum type already has a private constructor by default,
	// but if you need to initialize things...
	EnumSingleton() {
		value = 42;
	}

	// enums are inherently serializable (but what good is that?)
	// reflection not a problem, guaranteed 1 instance in JVM

	// field values not serialized!
	int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

public class SingletonEnumBasedDemo {

	static void saveToFile(EnumSingleton singleton, String filename) throws Exception {
		try (FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(singleton);
		}
	}

	static EnumSingleton readFromFile(String filename) throws Exception {
		try (FileInputStream fileIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			return (EnumSingleton) in.readObject();
		}
	}

	public static void main(String[] args) throws Exception {
		
		String filename = "myfile.bin";

	    // run again with next 3 lines commented out

//		EnumSingleton singleton = EnumSingleton.INSTANCE;
//	    singleton.setValue(111);
//	    saveToFile(singleton, filename);

		EnumSingleton singleton2 = readFromFile(filename);
	    System.out.println(singleton2.getValue());

	}
}
