package geometries;
import java.util.ArrayList;
import java.util.List;
import Primitives.Color;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import geometries.Intersectable.GeoPoint;
public class Triangle extends Polygon 
{
	/*
	 * constructor with 3 points ,use polygon test that use plane test(normal and point)-DRY principle
	 */
public Triangle(Point3D p1,Point3D p2,Point3D p3) throws Exception
{
	super(p1,p2,p3);
}

/*
 * constructor with color and 3 points
 */
public Triangle(Color color,Point3D p1,Point3D p2,Point3D p3) throws Exception
{
	super(color,p1,p2,p3);
}

/*
 * constructor with material, color and 3 points
 */
public Triangle(Material material,Color color,Point3D p1,Point3D p2,Point3D p3) throws Exception
{
	super(material,color,p1,p2,p3);
}

	
public Vector getNormal(Point3D point) throws Exception
{
	return super.getNormal(point).normalized();
}

//return the ray's intersection with the triangle - use the polygon's function(DRY)
public List<GeoPoint> findIntsersections(Ray ray) throws Exception
{
	List<GeoPoint> result = new ArrayList<>();
	result= super.findIntsersections(ray);
	List<GeoPoint> result1 = new ArrayList<>();
	if(result!=null)
	{
	for (GeoPoint geo : result) 
          result1.add(new GeoPoint(this, geo.point));
    return result1;
	}
	return null;
}

}
