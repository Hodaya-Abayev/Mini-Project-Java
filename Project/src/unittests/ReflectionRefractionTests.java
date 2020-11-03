/**
 * 
 */
package unittests;



import org.junit.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import Primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 *
 */
public class ReflectionRefractionTests {

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 * @throws Exception 
	 */
	/*@Test
	public void twoSpheres() throws Exception {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(
				new Sphere(new Material(0.4, 0.3, 100, 0.3, 0),new Color(java.awt.Color.BLUE),  
						new Point3D(0, 0, 50),50),
				new Sphere(new Material(0.5, 0.5, 100),new Color(java.awt.Color.RED), new Point3D(0, 0, 50),25));

		scene.addLights(new SpotLight(new Vector(-1, 1, 2),new Color(1000, 600, 0), new Point3D(-100, 100, -500), 1,0.0004, 0.0000006));

		ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render._imagewriter.writeToImage();
	}*/

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 * @throws Exception 
	 */
	/*@Test
	public void twoSpheresOnMirrors() throws Exception {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(10000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.addGeometries(
				new Sphere(new Material(0.25, 0.25, 20, 0.5, 0),new Color(0, 0, 100),  new Point3D(-950, 900, 1000),400),
				new Sphere(new Material(0.25, 0.25, 20),new Color(100, 20, 20),  new Point3D(-950, 900, 1000),200),
				new Triangle(new Material(0, 0, 0, 0, 1),new Color(20, 20, 20),  new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
				new Triangle( new Material(0, 0, 0, 0, 0.5),new Color(20, 20, 20), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

		scene.addLights(new SpotLight( new Vector(-1, 1, 4),new Color(1020, 400, 400),  new Point3D(-750, 750, 150),  1, 0.00001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render._imagewriter.writeToImage();
	}
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing partial shadow
	 * @throws Exception 
	 */
	
	/*@Test
	public void trianglesTransparentSphere() throws Exception {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( 
				new Triangle( new Material(0.5, 0.5, 60),Color.BLACK, 
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), 
				new Triangle(new Material(0.5, 0.5, 60),Color.BLACK,  
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), 
				new Sphere(new Material(0.2, 0.2, 30, 0.6, 0),new Color(java.awt.Color.BLUE),  
						new Point3D(60, -50, 50),30));

		scene.addLights(new SpotLight( new Vector(0, 0, 1), new Color(700, 400, 400), 
				new Point3D(60, -50, 0),1, 4E-5, 2E-7));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render._imagewriter.writeToImage();
	}*/
	
	/*
	 * Produce a picture of original ice cream lighted by a spot light with transparency and shadow
	 */
	@Test
	public void iceCreamTest() throws Exception
	{
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		
		scene.addGeometries( 
				new Triangle( new Material(0.5, 0.5, 60,0.5,0.4),new Color(255,204,153), 
						new Point3D(0, 100, 0), new Point3D(-50, 20, 0), new Point3D(50, 20, 0)), 
				new Sphere(new Material(0.2, 0.2, 30, 0, 0.6),new Color(java.awt.Color.PINK),  new Point3D(25, 5, 50),30),
		        new Sphere(new Material(0.2, 0.2, 30, 0, 0.4),new Color(java.awt.Color.ORANGE), new Point3D(-25, 5, 50),30),
				new Sphere(new Material(0.2, 0.2, 30, 0, 1),new Color(102,51,0),new Point3D(0, -30, 50),30));
		
		scene.addLights(new SpotLight( new Vector(0,0, 1), new Color(700, 400, 400), 
				new Point3D(60, -50, 0),1, 4E-5, 2E-7));

		ImageWriter imageWriter = new ImageWriter("iceCreamTest", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();
		render._isSuperSampling=false;

		render.renderImage();
		render._imagewriter.writeToImage();
	}
	
	
}

