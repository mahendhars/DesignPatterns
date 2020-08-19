package com.singleton.mulititon;

import java.util.HashMap;
import java.util.Map;

enum SubSystem {
	PRIMARY,
	AUXILARY,
	FALLBACK
}

class Printer {
	
	private static int instanceCount;
	
	private static Map<SubSystem, Printer> instanceMap = new HashMap<SubSystem, Printer>();
	
	private Printer() {
		instanceCount++;
		System.out.println("A total of " + instanceCount + " instances created so far.");
	}
	
	public static Printer getInstance(SubSystem subSystem) {
		if(instanceMap.get(subSystem) == null) {
			Printer printer = new Printer();
			instanceMap.put(subSystem, printer);
		} 
		
		return instanceMap.get(subSystem);
	}
}
public class SingletonMultitonDemo {
	
	public static void main(String[] args) {
		
		Printer primary1 = Printer.getInstance(SubSystem.PRIMARY);
		Printer primary2 = Printer.getInstance(SubSystem.PRIMARY);
		
		
		Printer auxilary1 = Printer.getInstance(SubSystem.AUXILARY);
		Printer auxilary2 = Printer.getInstance(SubSystem.AUXILARY);
		
		Printer fallback1 = Printer.getInstance(SubSystem.FALLBACK);
		Printer fallback2 = Printer.getInstance(SubSystem.FALLBACK);
	}
}
