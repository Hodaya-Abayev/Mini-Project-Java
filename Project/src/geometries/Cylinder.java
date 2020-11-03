package geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

public class Cylinder extends Tube {
double height;
	public Cylinder(double r, Ray ray, double h) {
		// TODO Auto-generated constructor stub
		super(r, ray);
		height =h;
	}
	
	@Override
	public String toString() {
		return "Cylinder [height=" + height + ", _axisRay=" + _axisRay + ", _radius=" + _radius + "]";
	}

	public double getHeight() {
		return height;
	}

	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

}
