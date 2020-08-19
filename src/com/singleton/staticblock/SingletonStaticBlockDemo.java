package com.singleton.staticblock;

import java.io.File;
import java.io.IOException;

class Singleton {
	
	private static Singleton instance;
	
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	private Singleton() throws IOException {
		System.out.println("Singleton is initializing");
		File.createTempFile(".", ".");
	}
	
	static {
		try {
			instance = new Singleton();
		} catch (Exception e) {
			System.err.println("Error creating Singleton");
		}
	}
	
	public static Singleton getInstance() {
		return instance;
	}
	
	@Override
	public String toString() {
		return "Singleton [value=" + value + "]";
	}
}
public class SingletonStaticBlockDemo {
	
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
	}
}
