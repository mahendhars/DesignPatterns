package com.decorator.statik;

import java.util.function.Supplier;

interface Shape {
	String info();
}

class Circle implements Shape {

	private float radius;
	
	public Circle() {}

	public Circle(float radius) {
		this.radius = radius;
	}

	@Override
	public String info() {
		return "Circle of radius " + radius;
	}
	
	public void resize(float factor) {
		radius *= radius;
	}
}

class Square implements Shape {
	
	private float size;
	
	public Square() {}

	public Square(float size) {
		this.size = size;
	}

	@Override
	public String info() {
		return "Square of size " + size;
	}
}

class ColoredShape<T extends Shape> implements Shape {
	
	private Shape shape;
	private String color;
	
	public ColoredShape(Supplier<? extends T> ctor, String color) {
		this.shape = ctor.get();
		this.color = color;
	}

	@Override
	public String info() {
		return shape.info() + " of color " + color;
	}
}

class TransparentShape<T extends Shape> implements Shape {
	
	private Shape shape;
	private int transparency;

	public TransparentShape(Supplier<? extends T> ctor, int transparency) {
		this.shape = ctor.get();
		this.transparency = transparency;
	}

	@Override
	public String info() {
		return shape.info() + " with " + transparency + "% transparency";
	}
}

public class DecoratorStaticCompositionDemo {

	public static void main(String[] args) {
		Circle circle = new Circle(10);
		System.out.println(circle.info());

		ColoredShape<Square> coloredSquare = new ColoredShape<>(() -> new Square(10), "blue");
		System.out.println(coloredSquare.info());

		// ugly!
		TransparentShape<ColoredShape<Circle>> myCircle = new TransparentShape<>(
				() -> new ColoredShape<>(() -> new Circle(5), "green"), 50);
		System.out.println(myCircle.info());
		// cannot call myCircle.resize()
	}
}
