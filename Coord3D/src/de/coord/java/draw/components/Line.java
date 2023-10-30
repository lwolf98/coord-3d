package de.coord.java.draw.components;

import de.coord.java.draw.CoordinateSystem;

public class Line extends Component {
	private Point p1, p2;
	private double m, t;
	
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		x = p1.x < p2.x ? p1.x : p2.x;
		y = p1.y < p2.y ? p1.y : p2.y;
		m = (p1.y - p2.y) / (p1.x - p2.x);
		//t = (m * p1.x) > 0 ? y - m * p1.x : y + m * p1.x;
		t = p1.y - m * p1.x;
	}
	
	public void draw(CoordinateSystem system) {
		system.getScreen().renderLine(system, this);
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}
	
	public double getM() {
		return m;
	}
	
	public double getT() {
		return t;
	}
}
