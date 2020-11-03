package elements;

import Primitives.Color;

public class AmbientLight extends Light {
 
/*
 * constructor 
 */
	public AmbientLight(Color color,double ka)
	{
		super(color.scale(ka));
	}

}

