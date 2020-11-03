/**
 * 
 */
package elements;

import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;

/**
 * @author осас
 *
 */
public class DirectionalLight extends Light implements LightSource {
	private  Vector _direction;
	/**
	 * constructor use Light's constructor 
	 * @throws Exception 
	 */
	public DirectionalLight(Color color,Vector direction) throws Exception 
	{
		super(color);
		this._direction=direction.normalize();
	}

	@Override
	/*
	 * return the intensity of the color
	 */
	public Color getIntensity(Point3D p)
	{
		return super.get_intensity();
	}

	@Override
	/*
	 * return the direction of the light
	 */
	public Vector getL(Point3D p) throws Exception  
	{
		return new Vector(_direction);
	}

	/*
	 * return the infinity distance between the directional light to the point
	 */
	public double getDistance(Point3D point) 
	{
		return  Double.POSITIVE_INFINITY;
	}

}
