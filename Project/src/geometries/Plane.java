package geometries;

import java.util.List;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import Primitives.Color;
import Primitives.Material;;
public class Plane extends Geometry
{
Point3D _p;
Vector _normal;
@Override
public  Vector getNormal(Point3D point) {
	return _normal.normalized();
}
/*
 * constructor with 3 points
 */
public Plane(Point3D p1, Point3D p2, Point3D p3) throws Exception
{
   Vector U = new Vector (p2.substract(p1));
   Vector V = new Vector (p3.substract(p1));
   Vector N = U.crossProduct(V).normalized();
    _normal=N;
	_p=p1;
}
/*
 * constructor with color and 3 points
 */
public Plane(Color color,Point3D p1, Point3D p2, Point3D p3) throws Exception
{

	this(p1,p2,p3);
	_emmission=color;
	
}
/*
 * constructor with material, color and 3 points
 */
public Plane(Material material, Color color,Point3D p1, Point3D p2, Point3D p3) throws Exception
{
this(color, p1, p2, p3);
_material=material;

}
public Plane(Point3D p, Vector normal)
{
	try
	{
	_p=p;
	_normal=normal.normalized();
	}
	catch (Exception e) {}
}

public Point3D get_p() {
	return _p;
}

public Vector get_normal() {
	return _normal.normalized();
}

@Override
public String toString() {
	return "Plane [_p=" + _p + ", _normal=" + _normal + "]";
}
 
@Override
/*
 * return the ray's intersectoin with the plane
 */
public List<GeoPoint> findIntsersections(Ray ray) throws Exception 
{
	Vector v;
	double mone;
	GeoPoint p1=new GeoPoint(this, ray.getPoint());
	try
	{
	    v=new Vector(_p.substract(p1.point));
	}
	catch(IllegalArgumentException e)
	{
		return null;
	}
    mone=_normal.dotProduct(v);
	double mechane=_normal.dotProduct(ray.getDirection());
	if(mechane==0)
		return null;
	if(mone==0)//the point is in the plane
		return List.of(p1);
	double t=mone/mechane;
	if(t<=0)
		return null;
	return List.of(new GeoPoint(this,ray.getPoint(t)));
	
}

}
