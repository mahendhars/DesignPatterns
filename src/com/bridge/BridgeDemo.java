package com.bridge;

interface Renderer {
	public void renderCircle(float radius);
	public void renderSquare(float size);
}

class VectorRenderer implements Renderer {

	@Override
	public void renderCircle(float radius) {
		System.out.println("Drawing vector circle of radius " + radius);
	}

	@Override
	public void renderSquare(float size) {
		System.out.println("Drawing vector square of size " + size);
	}
}

class RasterRenderer implements Renderer {

	@Override
	public void renderCircle(float radius) {
		System.out.println("Drawing raster circle of radius " + radius);
	}

	@Override
	public void renderSquare(float size) {
		System.out.println("Drawing raster square of size " + size);
	}
}

abstract class Shape {
	protected Renderer renderer;

	public Shape(Renderer renderer) {
		this.renderer = renderer;
	}	
	
	public abstract void draw();
	public abstract void resize(float factor);
}

class Circle extends Shape {
	public float radius;

	public Circle(Renderer renderer, float radius) {
		super(renderer);
		this.radius = radius;
	}

	@Override
	public void draw() {
		renderer.renderCircle(radius);
	}

	@Override
	public void resize(float factor) {
		radius *= factor;
	}
}

class Square extends Shape {
	public float size;

	public Square(Renderer renderer, float size) {
		super(renderer);
		this.size = size;
	}

	@Override
	public void draw() {
		renderer.renderSquare(size);
	}

	@Override
	public void resize(float factor) {
		size *= factor;
	}
}

public class BridgeDemo {
	
	public static void main(String[] args) {
		Renderer vectorRenderer = new VectorRenderer();
		Renderer rasterRenderer = new RasterRenderer();
		
		Circle circle = new Circle(vectorRenderer, 5);
		circle.draw();
		circle.resize(2);
		circle.draw();
		
		Square square = new Square(rasterRenderer, 3);
		square.draw();
		square.resize(4);
		square.draw();
		
		// using Dependency injection frameworks like Google Juice can make this even more streamlined 
	}
}
