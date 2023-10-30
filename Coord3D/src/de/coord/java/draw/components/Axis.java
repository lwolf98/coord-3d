package de.coord.java.draw.components;

import de.coord.java.draw.CoordinateSystem;

public class Axis extends Component {
	public enum Type { X, Y, Z }
	
	private Type type;
	
	public Axis(Type xyz) {
		type = xyz;
		/*how to handle?*/
		if(xyz == Type.X) x = 0;
		if(xyz == Type.Y) y = 0;
		if(xyz == Type.Z) z = 0;
	}

	public void draw(CoordinateSystem system) {
		system.getScreen().renderAxis(system, this);
	}
	
	public Type getType() {
		return type;
	}
}
