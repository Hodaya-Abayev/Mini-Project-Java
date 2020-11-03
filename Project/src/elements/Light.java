package elements;

import Primitives.Color;
import Primitives.Point3D;

public abstract class Light {
protected Color _intensity;

	public Light(Color intensity) 
	{
		_intensity=intensity;
	}
	
	/**
	 * @return the _intensity
	 */
	public Color get_intensity()
	{
		return new Color(_intensity);
	}
	
}
