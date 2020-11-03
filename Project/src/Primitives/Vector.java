package Primitives;

public class Vector
{
 Point3D vec;
 
 public Vector(Vector v)
 {
	 if(v.vec.equals(Point3D.zero))
		 throw new IllegalArgumentException("This point is zero");
	 else
       vec =new Point3D(v.vec);
 }
 
 public Vector(Point3D point) throws Exception
 {
	 if(point.equals(Point3D.zero))
		 throw new IllegalArgumentException("This point is zero");
	 else
		 vec=point;
 }
 
 public Vector(double x1,double y1,double z1) throws Exception
 {
	 Point3D p=new Point3D(x1,y1,z1);
	 if(p.equals(Point3D.zero))
		 throw new IllegalArgumentException("This point is zero");
	 else
	 {
		 vec= new Point3D(x1,y1,z1); 
	 }
 }

 public Vector(coordinate x1,coordinate y1,coordinate z1) throws Exception
 {
	 if(x1.get()==0&&y1.get()==0&&z1.get()==0)
		 throw new IllegalArgumentException("This point is zero");
	 else
		 vec=new Point3D(x1,y1,z1);
 }
 
 public Vector substract(Vector other) throws Exception
 {
	//return this.vec.substract(other.vec);
	
	 return new Vector(vec.x._coord-other.vec.x._coord,vec.y._coord-other.vec.y._coord,vec.z._coord-other.vec.z._coord);

 }
 
 public Vector add(Vector other) throws Exception
 {
	vec= new Point3D( this.vec.add((other))) ;
	return new Vector(vec);
 }
 public Vector scale(double scalar) throws Exception
 {
	// vec.x=new coordinate(vec.x.get()*scalar);
	// vec.y=new coordinate(vec.y.get()*scalar);
	// vec.z=new coordinate(vec.z.get()*scalar);
	 return new Vector(vec.x.get()*scalar,vec.y.get()*scalar,vec.z.get()*scalar);
 }
 
public Vector crossProduct(Vector B) throws Exception 
{
	  Vector v= new Vector(this);
	 double x=v.vec.y.get()*B.vec.z.get()-v.vec.z.get()*B.vec.y.get();
	 double y=v.vec.z.get()*B.vec.x.get()-v.vec.x.get()*B.vec.z.get();
	 double z=v.vec.x.get()*B.vec.y.get()-v.vec.y.get()*B.vec.x.get();
	 return new Vector(x,y,z);
}

public double dotProduct(Vector other)

{
	return util.alignZero(vec.x._coord*other.vec.x._coord+vec.y._coord*other.vec.y._coord+vec.z._coord*other.vec.z._coord);
}

public double lengthSquared()
{
	return(vec.x.get()*vec.x.get()+vec.y.get()*vec.y.get()+vec.z.get()*vec.z.get());
}

public double length()
{
	return Math.sqrt(lengthSquared());
}

public Vector normalize() throws Exception
{
	double len=length();
	vec.x=new coordinate(vec.x.get()/len);
	vec.y=new coordinate(vec.y.get()/len);
	vec.z=new coordinate(vec.z.get()/len);
	return this;
}

public Vector normalized()
{
	try
	{
	return new Vector(this.normalize());
	}
	catch  (Exception e) { return null;}
}
public Point3D getVec() {
	return vec;
}

@Override
public String toString()
{
	return "Vector [vec=" + vec + "]";
}


public boolean equals(Object obj) {
   if (this == obj) return true;
   if (obj == null) return false;
   if (!(obj instanceof Vector)) return false;
   Vector oth = (Vector)obj;
   return vec.equals(oth.vec);
}

 
 
}
