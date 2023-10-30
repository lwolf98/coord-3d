package de.position.java;

public class Calc {
	public static Point3D calculateNew(Point3D pnt, double a, double b, double g) {
		double xNew = 0, yNew = 0, zNew = 0;
		//x-Drehung
		xNew = pnt.x;
		yNew = pnt.z * Math.sin(Math.toRadians(a)) + pnt.y * Math.cos(Math.toRadians(a));
		zNew = pnt.z * Math.cos(Math.toRadians(a)) - pnt.y * Math.sin(Math.toRadians(a));
		Point3D resX = new Point3D(xNew, yNew, zNew);
		
		//y-Drehung
		xNew = resX.x * Math.cos(Math.toRadians(b)) - resX.z * Math.sin(Math.toRadians(b));
		//yNew = yNew; //For the sake of completeness
		zNew = resX.x * Math.sin(Math.toRadians(b)) + resX.z * Math.cos(Math.toRadians(b));
		Point3D resY = new Point3D(xNew, yNew, zNew);
		
		//z-Drehung
		xNew = resY.x * Math.cos(Math.toRadians(g)) - resY.y * Math.sin(Math.toRadians(g));
		yNew = resY.x * Math.sin(Math.toRadians(g)) + resY.y * Math.cos(Math.toRadians(g));
		//zNew = zNew; //For the sake of completeness
		Point3D resZ = new Point3D(xNew, yNew, zNew);
		
		return resZ;
	}
	
	public static Point getAbsolute(Point3D pnt, double phi, double q) {
		double x = pnt.x + pnt.z * Math.sin(Math.toRadians(phi)) * q;
		double y = pnt.y + pnt.z * Math.cos(Math.toRadians(phi)) * q;
		return new Point(x, y);
	}
	
	public static class Point {
		public double x;
		public double y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Point3D {
		public double x;
		public double y;
		public double z;
		
		public Point3D(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public static void main(String[] args) {
		Point3D p = new Point3D(0, 4, 1);
		double a = 0;
		double b = 0;
		double g = 90;
		Point3D pNew = calculateNew(p, a, b, g);
		Point pAbs = getAbsolute(pNew, 45, 0.5);
		System.out.println("P( " + p.x + " | " + p.y + " | " + p.z + ")");
		System.out.println("gedreht um: a = " + a + ", b = " + b + ", g = " + g);
		System.out.println("P( " + pNew.x + " | " + pNew.y + " | " + pNew.z + ")");
		System.out.println("gibt PAbs(" + pAbs.x + " | " + pAbs.y + ")");
	}
}
