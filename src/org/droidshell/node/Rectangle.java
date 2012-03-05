package org.droidshell.node;

import org.droidshell.math.Color;
import org.droidshell.math.Vector2D;

public class Rectangle extends Node {
	
	public float width;
	public float height;
	
	public Rectangle(Vector2D center, float width, float height) {
		super(center);
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Vector2D center, Color color, float width, float height) {
		super(center, color);
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Vector2D bottomLeft, Vector2D topLeft, Vector2D topRight, Vector2D bottomRight) {		
		super(bottomLeft.add(topLeft).add(topRight).add(bottomRight).divideN(4.0f));
	}

}
