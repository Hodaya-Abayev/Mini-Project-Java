/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import geometries.Geometries;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;


public class GeometriesTests {

	@Test
	public void testFindIntersections() throws Exception 
	{
		//TC01: Empty list (0 points)
		Geometries geo=new Geometries();
		assertEquals("Empty list",null,geo.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0))));
		//TC02: No geometry is intersected
		Sphere sphere1 = new Sphere(new Point3D(1, 0, 0), 1d);
		Sphere sphere2 = new Sphere(new Point3D(-1, 0, 0), 1d);
		Vector u= new Vector(1,2,3);
		Vector v= new Vector(0,1,0);
		Vector N = new Vector(u.crossProduct(v));
		Point3D p = new Point3D(2,0,0);
		Plane pl = new Plane(p, N);
		geo.add(pl,sphere1,sphere2);
		assertEquals("No geometry is intersected",0,geo.findIntsersections(new Ray(new Point3D(-5,-5,0),new Vector(0,-8,0))).size());
		//TC03: one geometry is intersected
		assertEquals("one geometry is intersected",1,geo.findIntsersections(new Ray(new Point3D(5,5,0),new Vector(0,2,1))).size());
		//TC04: two geometry are intersected
		assertEquals("two geometry is intersected",2,geo.findIntsersections(new Ray(new Point3D(-1,0,0),new Vector(1,3,0))).size());
		//TC05:all geometries are intersected
		Sphere sph1 = new Sphere(new Point3D(0.5, 0, 0), 1d);
		Sphere sph2 = new Sphere(new Point3D(-2, 0, 0), 1d);
		Vector u1= new Vector(1,2,3);
		Vector v1= new Vector(0,1,0);
		 N = new Vector(u1.crossProduct(v1));
		 p = new Point3D(2,0,0);
		 pl = new Plane(p, N);
		 Geometries g=new Geometries(sph1,sph2,pl);
		 assertEquals("all geometries are intersected",4,g.findIntsersections(new Ray(new Point3D(-2,0,0),new Vector(1,0,0))).size());
		 
	}

}

