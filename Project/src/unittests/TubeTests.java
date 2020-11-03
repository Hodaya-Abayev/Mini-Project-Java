package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import geometries.Tube;

public class TubeTests {

	@Test
	public void testGetNormal() throws Exception 
	{
		Point3D p=new Point3D(5, 4, 3);
		Vector v=new Vector(1, 2, 3);
		Ray ray=new Ray(p, v);
		Tube tube=new Tube(Math.sqrt(20), ray);
		Point3D point=new Point3D(9,7,6);
		Vector expected=new Vector(-15/66.075, -35/66.075, -54/66.075);
		Vector t= new Vector(tube.getNormal(point));
		assertEquals(expected.getVec().getX().get(),t.getVec().getX().get(),0.06);
		assertEquals(expected.getVec().getZ().get(),t.getVec().getZ().get(),0.06);
		assertEquals(expected.getVec().getY().get(),t.getVec().getY().get(),0.06);

	}

}

