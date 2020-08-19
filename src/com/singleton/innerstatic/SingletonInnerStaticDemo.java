package com.singleton.innerstatic;

class InnerStaticSingleton {
	
	private InnerStaticSingleton() {
		System.out.println("Initializing Singleton");
	}
	
	// static inner class
	private static class Impl {
		private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton(); 
	}
	
	public static InnerStaticSingleton getInstance() {
		return Impl.INSTANCE;
	}
}

public class SingletonInnerStaticDemo {
	
	public static void main(String[] args) {
		InnerStaticSingleton innerStaticSingleton = InnerStaticSingleton.getInstance();
	}
}
