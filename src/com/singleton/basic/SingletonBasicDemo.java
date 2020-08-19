package com.singleton.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class BasicSingleton implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3991118722135648018L;
	
	private static final BasicSingleton INSTANCE = new BasicSingleton();
	
	private BasicSingleton() {
		System.out.println("Singleton is initializing");
	}
	
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public static BasicSingleton getInstance() {
		return INSTANCE;
	}

	// required for correct serialization
	// readResolve is used for _replacing_ the object read from the stream
	protected Object readResolve() {
		return INSTANCE;
	}

	@Override
	public String toString() {
		return "BasicSingleton [value=" + value + "]";
	}
}

public class SingletonBasicDemo {
	
	public static void saveToFile(BasicSingleton basicSingleton, String filename) 
			throws Exception {
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
				ObjectOutputStream output = new ObjectOutputStream(fileOutputStream)) {
			output.writeObject(basicSingleton);
		}
	}
	
	public static BasicSingleton readFromFile(String filename) throws Exception {
		
		try(FileInputStream fileInputStream = new FileInputStream(filename);
				ObjectInputStream input = new ObjectInputStream(fileInputStream)) {
			
			return (BasicSingleton) input.readObject();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BasicSingleton basicSingleton1 = BasicSingleton.getInstance();
		basicSingleton1.setValue(123);
		BasicSingleton basicSingleton2 = BasicSingleton.getInstance();
		basicSingleton2.setValue(234);
		
		System.out.println(basicSingleton1 == basicSingleton2);
		System.out.println(basicSingleton1);
		System.out.println(basicSingleton2);
		
		// Singleton behavior can be defeated by Reflection and Serialization
		// this can overcome by using the readResolve() method in the Singleton class
		
		String filename = "singleton.bin";
		saveToFile(basicSingleton1, filename);
		BasicSingleton basicSingleton3 = readFromFile(filename);
		basicSingleton3.setValue(456);
		
		System.out.println(basicSingleton1 == basicSingleton3);
		System.out.println(basicSingleton1);
		System.out.println(basicSingleton3);
	}
}
