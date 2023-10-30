package de.coord.java.draw.components;

import de.coord.java.draw.CoordinateSystem;

public abstract class Component {
	protected double x;
	protected double y;
	protected double z;
	
	public abstract void draw(CoordinateSystem system);
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
