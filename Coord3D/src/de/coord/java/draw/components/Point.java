package de.coord.java.draw.components;

import de.coord.java.draw.CoordinateSystem;

public class Point extends Component {
	enum Type { Point, Cross }
	
	private Type type = Type.Cross;
	private int size = 3; //Pixel Precision
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void draw(CoordinateSystem system) {
		int[] area = CoordinateSystem.getDrawingArea(size);
		
		switch (type) {
			case Point:
				//Draw Point
				for(int i = 0; i < area.length; i++) {
					area[i] = CoordinateSystem.CLR_DEFAULT;
				}
				break;
				
			case Cross:
				//Draw Cross
				for(int i = 0; i < size; i++) {
					area[i + i * size] = CoordinateSystem.CLR_DEFAULT;
					area[i + (size - 1 - i) * size] = CoordinateSystem.CLR_DEFAULT;
				}
				break;
		}
		
		system.getScreen().renderPoint(system, this, area);
	}
}
