package com.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
	public String name = "Group";
	public String color;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GraphicObject() {
	}

	// the below construct allows individual and aggregate objects to be treated uniformly
	public List<GraphicObject> children = new ArrayList<GraphicObject>();

	private void print(StringBuilder stringBuilder, int depth) {
		stringBuilder.append(String.join("", Collections.nCopies(depth, "*"))).append(depth > 0 ? " " : "")
				.append((color == null || color.isEmpty()) ? "" : color + " ").append(getName())
				.append(System.lineSeparator());
		for (GraphicObject child : children)
			child.print(stringBuilder, depth + 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		print(sb, 0);
		return sb.toString();
	}
}

class Circle extends GraphicObject {

	public Circle(String color) {
		name = "Circle";
		this.color = color;
	}
}

class Square extends GraphicObject {

	public Square(String color) {
		name = "Square";
		this.color = color;
	}
}

public class CompositeDemo1 {
	
	public static void main(String[] args) {
		
		GraphicObject drawing = new GraphicObject();
		drawing.children.add(new Circle("Red"));
		drawing.children.add(new Square("Yellow"));
		
		GraphicObject group = new GraphicObject();
		group.children.add(new Circle("Blue"));
		group.children.add(new Square("Blue"));
		drawing.children.add(group);
		
		System.out.println(drawing);
	}
}
