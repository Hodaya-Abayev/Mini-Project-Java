package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import Primitives.Point3D;
import Primitives.Vector;
import elements.Camera;
import geometries.*;
public class IntegrationTests {

	@Test
	public void test() throws Exception
	{
		int mone =0;
		Sphere sp=new Sphere (new Point3D(0, 0, 3),1);
		Camera cam=new Camera(new Point3D(0, 0, 0),new Vector(0,0,1),new Vector(0,-1,0));
		//TC01:First test case(2)
		for(int j=0;j<3;j++)
		{
			for(int i=0;i<3;i++)
			{
				if(sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
				 mone+=sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
			}
		}
		assertEquals("First test case",2,mone);
	
	//TC02:Second test case(18)
	mone=0;
	 sp=new Sphere (new Point3D(0, 0, 2.5),2.5);
	 cam=new Camera(new Point3D(0, 0, -0.5),new Vector(0,0,1),new Vector(0,-1,0));

	for(int j=0;j<3;j++)
	{
		for(int i=0;i<3;i++)
		{
			if(sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
			 mone+=sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
		}
	}
	assertEquals("Second test case",18,mone);
	//TC03:Third test case(10)
	mone=0;
	sp=new Sphere (new Point3D(0, 0, 2),2);
	for(int j=0;j<3;j++)
	{
		for(int i=0;i<3;i++)
		{
			if(sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
			 mone+=sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
		}
	}
	assertEquals("Third test case",10,mone);
//TC04:Fourth test case(9)
	mone=0;
	sp=new Sphere (new Point3D(0, 0, 2),4);
	cam=new Camera(new Point3D(0, 0, 0),new Vector(0,0,1),new Vector(0,-1,0));
	for(int j=0;j<3;j++)
	{
		for(int i=0;i<3;i++)
		{
			if(sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
			 mone+=sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
		}
	}
	assertEquals("Fourth test case",9,mone);
	//TC05:Fifth test case(0)
	mone=0;
	sp=new Sphere (new Point3D(0, 0, -1),0.5);
	for(int j=0;j<3;j++)
	{
		for(int i=0;i<3;i++)
		{
			if(sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
			 mone+=sp.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
		}
	}
	assertEquals("Fifth test case",0,mone);
	//TC11: First test case(9)
	Plane pl=new Plane(new Point3D(0,0,1), new Point3D(3,0,1), new Point3D(3,3,1));
	mone=0;
	for(int j=0;j<3;j++)
	{
		for(int i=0;i<3;i++)
		{
			if(pl.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
			 mone+=pl.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
		}
	}
	assertEquals("First test case",9,mone);
	//TC12: Second test case(9)
	 pl=new Plane(new Point3D(3,2,0), new Point3D(0,2,2), new Point3D(1.5,1,1));
	mone=0;
	for(int j=0;j<3;j++)
	{
		for(int i=0;i<3;i++)
		{
			if(pl.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
			 mone+=pl.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
		}
	}
	assertEquals("Second test case",9,mone);
	
	//TC13: Third test case(6)

	 pl=new Plane(new Point3D(3,2,0), new Point3D(0,1,8), new Point3D(1.5,1,4));
		mone=0;
		for(int j=0;j<3;j++)
		{
			for(int i=0;i<3;i++)
			{
				if(pl.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
				 mone+=pl.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
			}
		}
		assertEquals("Third test case",6,mone);
	
		//TC21: First test case(1)
	Triangle tr=new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
	mone=0;
	for(int j=0;j<3;j++)
	{
		for(int i=0;i<3;i++)
		{
			if(tr.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
			 mone+=tr.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
		}
	}
	assertEquals("Third test case",1,mone);
	//TC22: First test case(2)
	 tr=new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
	mone=0;
	for(int j=0;j<3;j++)
	{
		for(int i=0;i<3;i++)
		{
			if(tr.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3))!=null)
			 mone+=tr.findIntsersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3)).size();
		}
	}
	assertEquals("Third test case",2,mone);
}
}