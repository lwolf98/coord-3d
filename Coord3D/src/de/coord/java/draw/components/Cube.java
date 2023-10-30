package de.coord.java.draw.components;

import de.coord.java.draw.CoordinateSystem;
import de.position.java.Calc;

public class Cube extends Component {
	private Calc.Point3D[] points = new Calc.Point3D[8];
	
	public Cube(Calc.Point3D pos, double l) {
		points[0] = new Calc.Point3D(pos.x, pos.y, pos.z);
		points[1] = new Calc.Point3D(pos.x + l, pos.y, pos.z);
		points[2] = new Calc.Point3D(pos.x + l, pos.y, pos.z + l);
		points[3] = new Calc.Point3D(pos.x, pos.y, pos.z + l);
		points[4] = new Calc.Point3D(pos.x, pos.y + l, pos.z);
		points[5] = new Calc.Point3D(pos.x + l, pos.y + l, pos.z);
		points[6] = new Calc.Point3D(pos.x + l, pos.y + l, pos.z + l);
		points[7] = new Calc.Point3D(pos.x, pos.y + l, pos.z + l);
	}
	
	public void printPoints() {
		for(int i = 0; i < points.length; i++)
			System.out.println("P" + i + "( " + points[i].x + " | " + points[i].y + " | " + points[i].z + " )");
	}
	
	@Override
	public void draw(CoordinateSystem system) {
		Point[] pnts = new Point[points.length];
		for(int i = 0; i < points.length; i++) {
			Calc.Point pnt = Calc.getAbsolute(Calc.calculateNew(points[i], system.alpha, system.beta, system.gamma), 45, 0.5);
			pnts[i] = new Point(pnt.x, pnt.y);
			pnts[i].draw(system);
		}
		//TODO: Line-Stuff wo anders handlen
		new Line(pnts[0], pnts[1]).draw(system);
		new Line(pnts[1], pnts[2]).draw(system);
		new Line(pnts[2], pnts[3]).draw(system);
		new Line(pnts[3], pnts[0]).draw(system);
		new Line(pnts[0], pnts[4]).draw(system);
		new Line(pnts[1], pnts[5]).draw(system);
		new Line(pnts[2], pnts[6]).draw(system);
		new Line(pnts[3], pnts[7]).draw(system);
		new Line(pnts[4], pnts[5]).draw(system);
		new Line(pnts[5], pnts[6]).draw(system);
		new Line(pnts[6], pnts[7]).draw(system);
		new Line(pnts[7], pnts[4]).draw(system);
	}
}
