/**
 * 
 */
package elements;

import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;
import Primitives.util;

/**
 * @author осас
 *
 */
public class PointLight extends Light implements LightSource {
protected Point3D _position;
protected double _Kc;
protected double _Kl;
protected double _Kq;
	
     /**
	 * constructor use Light's constructor
	 */
	public PointLight(Color color,Point3D pos,double kc,double kl,double kq)
	{
		super(color);
		_position=pos;
		_Kc=kc;// attenuation with distance factors
		_Kl=kl;
		_Kq=kq;
	}

	
	/*
	 * overriding LightSource getIntensity(Point3D)
	 */
	@Override

	public Color getIntensity(Point3D p) throws Exception
	{
		double d=p.distance(_position);
		double mechane= _Kc+_Kl*d+ _Kq*d*d;
		if(util.isZero(mechane))
			throw new IllegalArgumentException("mechane is zero");
		return _intensity.reduce(mechane);
	}

	@Override
	/*
	 *  Light vector
	 */
	public Vector getL(Point3D p) throws Exception 
	{
		   if (p.equals(_position)) 
		   {
	            return null;
	       }
		return p.substract(_position).normalize();
	}


	@Override
	public double getDistance(Point3D point)
	{
		return point.distance(_position);
	}
	
	

}
