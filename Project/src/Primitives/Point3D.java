package Primitives;
import java.util.List;

import Primitives.Vector;
public class Point3D 
{
  coordinate x;
  coordinate y;
  coordinate z;
  
 public static Point3D zero = new Point3D(0,0,0);
  public Point3D(coordinate x1,coordinate y1,coordinate z1)
  {
	  x=new coordinate(x1);
	  y=new coordinate(y1);
	  z=new coordinate(z1);
  }
  
  public Point3D(Point3D point)
  {
	  x=new coordinate(point.x);
	  y=new coordinate(point.y);
	  z=new coordinate(point.z);
  }

public Point3D(double x1,double y1,double z1)
{
	x=new coordinate(x1);
	y=new coordinate(y1);
	z=new coordinate(z1);
}
public coordinate getX() {
	return x;
}

public coordinate getY() {
	return y;
}


public coordinate getZ() {
	return z;
}

@Override
public String toString() {
	return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
}

public boolean equals(Object obj)
{
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof Point3D)) return false;
    Point3D oth = (Point3D)obj;
    return x.equals(oth.x) && y.equals(oth.y) && z.equals(oth.z)   ;
 }

public Vector substract(Point3D point) throws Exception
{

	Vector v=new Vector(new coordinate(x.get()-point.x.get()),new coordinate(y.get()-point.y.get()),new coordinate(z.get()-point.z.get()));
	return v;
	
}
public Point3D add(Vector vector)
{
	Point3D p=new Point3D(new coordinate(x.get()+vector.vec.x.get()), new coordinate(y.get()+vector.vec.y.get()), new coordinate(z.get()+vector.vec.z.get()));
	return p;
}

public double distanceSquared(Point3D point)
{
	double distancex = x.get()-point.x.get();
	double distancey = y.get()-point.y.get();
	double distancez = z.get()-point.z.get();
	return (distancex*distancex+distancey*distancey+distancez*distancez);
}
  
public double 	distance(Point3D point)
{
	return Math.sqrt(this.distanceSquared(point)) ;
}
}
