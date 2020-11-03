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
public class SpotLight extends PointLight {
private Vector _direction;
	/**
	 * constructor use PointLight's constructor
	 * @throws Exception 
	 */
	public SpotLight(Vector direction,Color color,Point3D pos,double kc,double kl,double kq) throws Exception
	{
		super(color,pos,kc,kl,kq);
		_direction=direction.normalize();
	}
	
	@Override
	/*
	 * overriding PointLight getIntensity(Point3D)
	 */
	public Color getIntensity(Point3D p) throws Exception 
	{
		/*
		Vector dir=super.getL(p);
		double l=dir.dotProduct(_direction);
		if(l<=0)
			return null;
		return super.getIntensity(p).scale(l);
		*/
		double projection = _direction.dotProduct(getL(p));

        if (util.isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointlightIntensity = super.getIntensity(p);

       

        return (pointlightIntensity.scale(factor));
		
	}
	/*
	 * return the distance between the spot light and the point ,using pointlight function
	 */
	public double getDistance(Point3D point) 
	{
		return super.getDistance(point);
	}




}
