package geometries;
import java.util.List;

import Primitives.Point3D;
import Primitives.Ray;

public interface Intersectable
{
	List<GeoPoint> findIntsersections(Ray ray)throws Exception ;
	/*
	 * help class
	 */
	public static class GeoPoint 
	{
	    public Geometry geometry;
	    public Point3D point;
	    /*
	     * constructor
	     */
	    public GeoPoint(Geometry geo,Point3D p)
	    {
	    	geometry=geo;
	    	point=p;
	    }
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}
	    
	}


}
