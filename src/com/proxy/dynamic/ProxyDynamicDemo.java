package com.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
	void walk();
	void talk();
}

class Person implements Human {

	@Override
	public void walk() {
		System.out.println("I am walking");
	}

	@Override
	public void talk() {
		System.out.println("I am talking");
	}
	
}

class LoggingHandler implements InvocationHandler {
	
	private final Object target;
	private Map<String, Integer> calls = new HashMap<String, Integer>();
	
	public LoggingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		
		if(methodName.contains("toString")) {
			return calls.toString();
		}
		
		calls.merge(methodName, 1, Integer::sum);
		return method.invoke(target, args);
	}
}
public class ProxyDynamicDemo {
	@SuppressWarnings("unchecked")
	public static <T> T withLogging(T target, Class<T> interfaces) {
		
		return (T) Proxy.newProxyInstance(
				interfaces.getClassLoader(), 
				new Class<?>[]{interfaces}, 
				new LoggingHandler(target));
	}
	
	public static void main(String[] args) {
		Person person = new Person();
		Human logged = withLogging(person, Human.class);
		logged.talk();
		logged.talk();
		logged.walk();
		System.out.println(logged);
	}
}
