package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import geometries.Plane;

public class PlaneTests {

	@Test
	public void testGetNormal() throws Exception
	{
		Vector u= new Vector(1,2,3);
		Vector v= new Vector(5,4,3);
		Vector N = u.crossProduct(v);
		Point3D p = new Point3D(1,2,3);
		Plane pl=new Plane(p, N);
        assertEquals(pl.getNormal(p),N);
	}
	
	@Test
	public void testfindIntsersections() throws Exception
	{
		Vector u= new Vector(1,2,3);
		Vector v= new Vector(5,4,3);
		Vector N = new Vector(u.crossProduct(v));
		Point3D p = new Point3D(1,2,3);
		Plane pl = new Plane(p, N);
		 // TC01: Ray's line is parallel  the plane (0 points)
		Vector vec=new Vector(0,6,12);
		Point3D point = new Point3D(1,1,-1);
		Ray ray =new Ray(point, vec);
		assertEquals("Ray's line is parallel  the plane",null,pl.findIntsersections(ray));
		 // TC02: Ray's line is include  the plane (0 points)
		 point = new Point3D(1,2,3);
		 ray =new Ray(point, vec);
		assertEquals("Ray's line is include  the plane",null,pl.findIntsersections(ray));
		// TC03: Ray's line is orthogonal  the plane,after (0 points)
		 point=new Point3D(1,1,0);
		 ray=new Ray(point,N);
		 assertEquals("Ray's line is orthogonal  the plane,after",null,pl.findIntsersections(ray));
		// TC04: Ray's line is orthogonal  the plane,before (1 points)
		point= new Point3D(-6,12,-6);
	    ray=new Ray(point,N.scale(-1));
	    Point3D excepted=new Point3D(0,0,0);
		assertEquals("Ray's line is orthogonal  the plane,before",excepted,pl.findIntsersections(ray).get(0).point);
		// TC05: Ray's line is orthogonal  the plane,in (1 points)
		 point=new Point3D(5,4,3);
		 ray=new Ray(point,N);
		assertEquals("Ray's line is orthogonal  the plane,in",point,pl.findIntsersections(ray).get(0).point);
		//TC06:The ray intersected the plane
		assertEquals("The ray intersected the plane",new Point3D(0,0,0),pl.findIntsersections(new Ray(new Point3D(-1,0,0), new Vector(1, 0, 0))).get(0).point);
		//TC07: Ray begins at the same point which appears as reference point in the plane
		point=new Point3D(1,2,3);
		assertEquals("Ray begins at the same point which appears as reference point in the plane",null,pl.findIntsersections(new Ray(new Point3D(1, 2, 3), new Vector(1, 0, 0))));
		//TC08: Ray begins at the plane
		point=new Point3D(0,0,0);
		assertEquals(" Ray begins at the plane",point,pl.findIntsersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0))).get(0).point);
		//TC06:The ray doesnt intersected the plane
		assertEquals("The ray doesnt intersected the plane",null,pl.findIntsersections(new Ray(new Point3D(-1,0,0), new Vector(0, 3, 0))));
	}

}
