package geometries;

import java.util.ArrayList;
import java.util.List;

import Primitives.Point3D;
import Primitives.Ray;

public class Geometries implements Intersectable  {

	private List<Intersectable> ls;
	
	public Geometries()
	{
	  ls=new ArrayList<Intersectable>();	 
	}

	public Geometries(Intersectable... geometries)
	{
		ls=new ArrayList<Intersectable>();
		for(int i=0;i<geometries.length;i++)
			ls.add(geometries[i]);
	}
    
	public void add(Intersectable... geometries)
	{
		for(int i=0;i<geometries.length;i++)
			ls.add(geometries[i]);
	}
	@Override
	public List<GeoPoint> findIntsersections(Ray ray) throws Exception
	{
		List<GeoPoint> result=new ArrayList<>();
		if(ls.isEmpty())
			return null;
		for (Intersectable item : ls)
		{
			if(item.findIntsersections(ray)!=null)
				for(int i=0;i<item.findIntsersections(ray).size();i++)
					result.add((item.findIntsersections(ray)).get(i));
		}
		return result;
	}

}
