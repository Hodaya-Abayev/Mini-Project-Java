package Primitives;

public class Ray
{
Point3D point;
Vector direction;

/*
 * floating character
 */
private static final double DELTA = 0.1;

/*
 * copy constructor
 */
public Ray(Ray other)
{
	
	point=new Point3D(other.point);
	direction=new Vector(other.direction);
}

/*
 * constructor - get point and vector
 */
public Ray(Point3D point1, Vector direct)
{
	point=point1;
	direction=direct;
}

/*
 * constructor 
 */
public Ray(Point3D point1, Vector direct,Vector normal) throws Exception
{
double sign=direct.dotProduct(normal);
if(sign>0)
	point=point1.add(normal.scale(DELTA));
else
	point=point1.add(normal.scale(-DELTA));
direction=direct;
}

/*
 * rturn the ray's start point
 */
public Point3D getPoint() 
{
	return point;
}

/*
 * return the ray's direction
 */
public Vector getDirection() throws Exception 
{
	return direction.normalize();
}

@Override
public String toString() 
{
	return "Ray [point=" + point + ", direction=" + direction + "]";
}


public boolean equals(Object obj) 
{
   if (this == obj) return true;
   if (obj == null) return false;
   if (!(obj instanceof Ray)) return false;
   Ray oth = (Ray)obj;
   return point.equals(oth.point) && direction.equals(oth.direction);
}

/*
 * get a scalar and return the point in the continue of the ray
 */
public  Point3D getPoint(double t) throws Exception 
{
	if(util.isZero(t))
		return point;
	Vector d=new Vector(direction.scale(t));
	Point3D p=new Point3D(point.add(d));
	return p;
}



}

