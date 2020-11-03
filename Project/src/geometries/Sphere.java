package geometries;
import Primitives.Color;

import Primitives.Material;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import Primitives.util;
import java.util.List;

public class Sphere extends RadialGeometry 
{
Point3D _center;
	
	
/*
 * constructor with center point and radius
 */
	public Sphere(Point3D center, double r)
	{
		// TODO Auto-generated constructor stub
		super(r);
		_center=center;
	}
	
	/*
	 * constructor with color, center point and radius
	 */
	public Sphere(Color color,Point3D center, double r) 
	{ 
		this(center,r);
		_emmission=color;
	}
	/*
	 * constructor with material, color, center point and radius
	 */
	public Sphere(Material material,Color color,Point3D center, double r) 
	{ 
		this(color,center, r);
		_material=material;
	}


	public Point3D get_center() {
		return _center;
	}



	@Override
	public String toString() {
		return "Sphere [_center=" + _center + ", _radius=" + _radius + "]";
	}



	@Override
	/*
	 * calculate the normal and return it
	 */
	public Vector getNormal(Point3D p) throws Exception
	{
		return new Vector((p.substract(_center)).normalize());
	}



	@Override
/*
 * return the ray's intersections with the sphere
 */
	public List<GeoPoint> findIntsersections(Ray ray) throws Exception
	{
		Point3D p0=ray.getPoint();
		Vector v=ray.getDirection();
		Vector u;
		try
		{
			u=_center.substract(p0);
		}
		catch(IllegalArgumentException ex)
		{
			return List.of(new GeoPoint(this,ray.getPoint(_radius)));
		}
		double tm=util.alignZero(v.dotProduct(u));
		double dSquared;
	    if(tm==0)
	    {
	    	dSquared=util.alignZero((u.lengthSquared()));
	    }
	    else
	    {
		dSquared=util.alignZero((u.lengthSquared()-(tm*tm)));
	    }
	    double thSquared = util.alignZero(_radius*_radius-dSquared);
	    if(thSquared<=0)
	    {
	    	return null;
	    }
		double th=util.alignZero(Math.sqrt(thSquared));
		if(th==0)
			return null;
		double t1=util.alignZero(tm+th);
		double t2=util.alignZero(tm-th);
        if(t1<=0&&t2<=0)
        	return null;
        if(t1>0&&t2>0)
        	return List.of(new GeoPoint(this,ray.getPoint(t1)),new GeoPoint(this,ray.getPoint(t2)));
        if(t1>0)
        	return List.of(new GeoPoint(this,ray.getPoint(t1)));
        else
        	return List.of(new GeoPoint(this,ray.getPoint(t2)));
		}
	

}
