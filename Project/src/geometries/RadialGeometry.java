package geometries;

public abstract class RadialGeometry extends Geometry
{
double _radius;

public RadialGeometry(RadialGeometry radial)
{
	_radius=radial._radius;
}

public double get_radius() {
	return _radius;
}

public RadialGeometry(double r)
{
	_radius=r;
}

}
