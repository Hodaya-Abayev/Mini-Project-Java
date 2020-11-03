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
public interface LightSource {
	/*
	 * get point and return intensity
	 */
	public Color getIntensity(Point3D p) throws Exception;
	/*
	 * get point and return direction vector
	 */
	public Vector getL(Point3D p) throws Exception;
	/*
	 * return the distance between the point and the light
	 */
	double getDistance(Point3D point);

}
