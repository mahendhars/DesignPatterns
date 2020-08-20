package com.proxy.property;

class Property<T> {
	T value;

	
	public Property(T value) {
		// we can do some logging here 
		this.value = value;
	}

	public T getValue() {
		// we can do some logging here 
		return value;
	}

	public void setValue(T value) {
		// we can do some logging here 
		this.value = value;
	}
}
class Creature {
	Property<Integer> agility = new Property<>(10);

	public Integer getAgility() {
		return agility.getValue();
	}

	public void setAgility(int value) {
		// we cannot do agility = value, sadly
		this.agility.setValue(value);
	}
}
public class ProxyPropertyDemo {
	
	public static void main(String[] args) {
		Creature creature = new Creature();
		creature.setAgility(12);
	}
}
