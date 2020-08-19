package com.singleton.lazy;

class LazySingleton {
	
	private static LazySingleton instance;
	
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private LazySingleton() {
		System.out.println("Singleton is lazily initializing");
	}
	
	// to ensure thread safety mark the method as synchronized
//	public static synchronized LazySingleton getInstance() {
//		
//		if (instance == null) {
//			instance = new LazySingleton();
//		} 
//		
//		return instance;
//	}

	// double checked locking for thread safety
	public static LazySingleton getInstance() {

		if (instance == null) {
			synchronized (LazySingleton.class) {
				if(instance == null) {
					instance = new LazySingleton();
				}
			}
		}

		return instance;
	}
	
	@Override
	public String toString() {
		return "LazySingleton [value=" + value + "]";
	}
}
public class SingletonLazyDemo {
	
	public static void main(String[] args) {
		
		LazySingleton lazySingleton = LazySingleton.getInstance();
		lazySingleton.setValue(123);
		
		System.out.println(lazySingleton);
	}
}
