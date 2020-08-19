package com.factory.classes;

class Point {
	private double x;
	private double y;

	private Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	// Factory class
	static class Factory {
		// intuitive and descriptive Factory methods
		public static Point newCartesionPoint(double x, double y) {
			return new Point(x, y);
		}

		// intuitive and descriptive Factory methods
		public static Point newPolarPoint(double rho, double theta) {
			return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
		}
	}
}

public class FactoryClassDemo {

	public static void main(String[] args) {
		Point point1 = Point.Factory.newCartesionPoint(10, 10);
		System.out.println(point1);
		Point point2 = Point.Factory.newPolarPoint(10, 10);
		System.out.println(point2);

	}
}
