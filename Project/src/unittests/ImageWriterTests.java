package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import Primitives.Color;

import renderer.ImageWriter;

public class ImageWriterTests {

	@Test
	/*
	 * Produce an image with background color and grid
	 */
	public void test() 
	{
		
		ImageWriter ir=new ImageWriter("my image",1600,1000,800,500);
		for (int j = 0; j < 500; j++) 
		{
			for (int i = 0; i <800; i++) 
			{
			if(i%50==0||j%50==0)
				ir.writePixel(i, j, new Color(255,255,255).getColor());
			else
			ir.writePixel(i, j,  Color.BLACK.getColor());	
			}
		}
		ir.writeToImage();
	}
	

}
