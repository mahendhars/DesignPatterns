package com.decorator.dynamic;

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

class ColoredShape implements Shape {
	
	private Shape shape;
	private String color;
	
	public ColoredShape(Shape shape, String color) {
		this.shape = shape;
		this.color = color;
	}

	@Override
	public String info() {
		return shape.info() + " and color " + color;
	}
}

class TransparentShape implements Shape {

	private Shape shape;
	private int transparency;
	
	public TransparentShape(Shape shape, int transparency) {
		this.shape = shape;
		this.transparency = transparency;
	}

	@Override
	public String info() {
		return shape.info() + " with " + transparency + "% transparency";
	}
}

public class DecoratorDynamicCompositionDemo {
	
	public static void main(String[] args) {
		Circle circle = new Circle(5);
		System.out.println(circle.info());
		
		ColoredShape coloredSquare = new ColoredShape(new Square(10), "blue");
		System.out.println(coloredSquare.info());
		
		TransparentShape transparentCircle = new TransparentShape(new ColoredShape(new Circle(20), "green"), 50);
		System.out.println(transparentCircle.info());
	}
}
