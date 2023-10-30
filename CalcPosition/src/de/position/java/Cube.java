package de.position.java;

public class Cube {
	private Calc.Point3D[] points = new Calc.Point3D[8];
	
	public Cube(Calc.Point3D pos, double l) {
		points[0] = new Calc.Point3D(pos.x, pos.y, pos.z);
		points[1] = new Calc.Point3D(pos.x + l, pos.y, pos.z);
		points[2] = new Calc.Point3D(pos.x + l, pos.y, pos.z + l);
		points[3] = new Calc.Point3D(pos.x, pos.y, pos.z + l);
		points[4] = new Calc.Point3D(pos.x, pos.y + l, pos.z);
		points[5] = new Calc.Point3D(pos.x + l, pos.y + l, pos.z);
		points[6] = new Calc.Point3D(pos.x + l, pos.y + l, pos.z + l);
		points[7] = new Calc.Point3D(pos.x, pos.y + l, pos.z);
	}
	
	public void draw() {
		for(int i = 0; i < points.length; i++);
			//system.add(new DrawPoint(Calc.getAbsolute(points[i], 45, 0.5))); 
	}
}
