/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import geometries.Intersectable.GeoPoint;
import geometries.Sphere;

/**
 * @author осас
 *
 */
public class SphereTests 
{

	/**
	 * Test method for {@link geometries.Sphere#getNormal(Primitives.Point3D)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetNormal() throws Exception
	{
		Point3D center =new Point3D(1, 2, 3);
		Point3D p=new Point3D(5, 4, 3);
		Point3D p1=new Point3D(p);
		Vector n=new Vector((p.substract(center)).normalize());
		Sphere s=new Sphere(center, Math.sqrt(20));
		assertEquals(n, s.getNormal(p1));
	}
	

    @Test
    public void testFindIntersections() throws Exception
    {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
            sphere.findIntsersections(new Ray(new Point3D(-1,0,0), new Vector(1,1,0))));

         //TC02: Ray starts before and crosses the sphere (2 points)
       GeoPoint p1 = new GeoPoint(sphere,new Point3D(0.0651530771650466, 0.355051025721682, 0));
       GeoPoint p2 = new GeoPoint(sphere,new Point3D(1.53484692283495, 0.844948974278318, 0));
       List<GeoPoint> result = sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
                                                              
      assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).point.getX().get() > result.get(1).point.getX().get())
           result = List.of(result.get(1), result.get(0));
       assertEquals("Ray crosses sphere", result,List.of(p1, p2));

        // TC03: Ray starts inside the sphere (1 point)
       GeoPoint ex=new GeoPoint(sphere,new Point3D(1,1,0));
       assertEquals("Ray's line inside sphere", ex,sphere.findIntsersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0))).get(0));
        // TC04: Ray starts after the sphere (0 points)
       assertEquals("Ray starts after the sphere", null,sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(-3, 1, 0))));


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
       assertEquals("Ray starts at the sphere and goes inside", new Point3D(1,0,1 ),sphere.findIntsersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 1))).get(0).point);
        // TC12: Ray starts at sphere and goes outside (0 points)
       assertEquals("Ray starts at the sphere and goes outside", null ,sphere.findIntsersections(new Ray(new Point3D(0, 0, 0), new Vector(-1, 0, 1))));
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
       result = List.of(new GeoPoint(sphere,new Point3D(2,0,0)), new GeoPoint(sphere,new Point3D(0,0,0)));
       assertEquals("Ray starts before the sphere", result ,sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 0))));
        // TC14: Ray starts at sphere and goes inside (1 points)
       assertEquals("Ray starts at the sphere and goes inside", new Point3D(2,0,0 ),sphere.findIntsersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 0, 0))).get(0).point);
        // TC15: Ray starts inside (1 points)
       assertEquals("Ray starts inside", new Point3D(2,0,0 ),sphere.findIntsersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0))).get(0).point);
        // TC16: Ray starts at the center (1 points)
       assertEquals("Ray starts at the center", new Point3D(2,0,0 ),sphere.findIntsersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0))).get(0).point);
        // TC17: Ray starts at sphere and goes outside (0 points)
       assertEquals("Ray starts at the sphere and goes outside", null ,sphere.findIntsersections(new Ray(new Point3D(0, 0, 0), new Vector(-1, 0, 1))));
        // TC18: Ray starts after sphere (0 points)
       assertEquals("Ray starts after the sphere", null,sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(-3, 1, 0))));
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
       assertEquals(" Ray starts before the tangent point", null,sphere.findIntsersections(new Ray(new Point3D(0, 0, -2), new Vector(0,0, 1))));
        // TC21: Ray starts after the tangent point
       assertEquals(" Ray starts before the tangent point", null,sphere.findIntsersections(new Ray(new Point3D(0, 0, 2), new Vector(0,0, 1))));
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line

    }


}

