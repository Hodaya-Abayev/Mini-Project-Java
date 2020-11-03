package geometries;
import Primitives.Color;
import Primitives.Material;

import java.util.List;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

public abstract class  Geometry implements Intersectable
{
protected Color _emmission;
protected Material _material;

public  Geometry(Color color, Material material)
{
	_emmission=color;
	_material=material;
}


/**
 * @return the _material
 */
public Material get_material() {
	return _material;
}

public abstract Vector getNormal(Point3D point) throws Exception;

public abstract List<GeoPoint> findIntsersections(Ray ray) throws Exception ;

/**
 * @return the _emmission
 */
public Color get_emmission()
{
	return _emmission;
}
/*
 * constructor
 */
public  Geometry(Color color)
{
//this(color, new Material(0.0, 0.0, 0));
	_emmission=color;
	_material= new Material(0.0, 0.0, 0);	
}
/*
 * default constructor
 */
public  Geometry()
{
	_emmission=Color.BLACK;
}
}
