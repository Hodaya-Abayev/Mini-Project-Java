package unittests;
import renderer.ImageWriter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import elements.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import Primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Test rendering abasic image
 * 
 * @author Dan
 */
public class RenderTests 
{

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     * @throws Exception 
     */
	
	
	
   @Test
    public void basicRenderTwoColorTest() throws Exception {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(Point3D.zero, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(100);
        scene.set_background(new Color(75, 127, 90));
        scene.set_ambientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(new Sphere(new Point3D(0, 0, 100),50 ));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50, java.awt.Color.YELLOW);
        render._imagewriter.writeToImage();
    }

	 
    @Test
   
    public void basicRenderMultiColorTest() throws Exception 
    {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(Point3D.zero, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(100);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));

        scene.addGeometries(new Sphere(new Point3D(0, 0, 100),50 ));

        scene.addGeometries(
                        new Triangle(new Color(java.awt.Color.BLUE),
                        new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),      // lower right
                new Triangle(
                        new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),    // upper right
                new Triangle(new Color(java.awt.Color.RED),
                        new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),    // lower left
                new Triangle(new Color(java.awt.Color.GREEN),
                        new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100))); // upper left
       
        
        ImageWriter imageWriter = new ImageWriter("color render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.printGrid(50, java.awt.Color.WHITE);
        render._imagewriter.writeToImage();
    }
    
	@Test
	 
	public void getClosestPoint() throws Exception
	{
        Scene scene = new Scene("Test scene");
        ImageWriter imageWriter = new ImageWriter("color render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
		Sphere sphere=new Sphere(new Point3D(1,0,0),1);
		GeoPoint p=new GeoPoint(sphere,new Point3D(0,0,0));
		assertEquals("check closestPoint",p.point,render.getClosestPoint(sphere.findIntsersections(new Ray(new Point3D(-1,0,0),new Vector(1,0,0)))).point);
	}
	
	
/*
* test to check the super sampling
*/
	 @Test
	public void check() throws Exception
	{
	        Scene scene = new Scene("Test scene");
	        scene.set_camera(new Camera(Point3D.zero, new Vector(0, 0, 1), new Vector(0, -1, 0)));
	        scene.set_distance(100);
	        scene.set_background(new Color(0,0,0));
	        scene.set_ambientLight(new AmbientLight(new Color(255, 255, 255), 0));
	        
	        scene.addGeometries(new Sphere(new Material(0.5, 0.5, 50),new Color(153,255,255),new Point3D(-100,-100,100),30),
	        		new Sphere(new Material(0.5, 0.5, 50),new Color(255,255,153),new Point3D(100, -100,100),30 ),
	        		new Sphere(new Material(0.5, 0.5, 50),new Color(255,204,204),new Point3D(0, 0, 100),30 ),
	        		new Sphere(new Material(0.5, 0.5, 50),new Color(204,255,153),new Point3D(-100,100, 100),30 ),
	                new Sphere(new Material(0.5, 0.5, 50),new Color(255,128,0),new Point3D(100,100, 100),30 ));
	        
	        ImageWriter imageWriter = new ImageWriter("check", 500, 500, 500, 500);
	        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();
	        render._isSuperSampling=true;
	        render.renderImage();
	        render._imagewriter.writeToImage();
	}
	 /*
	 * the final image
	 */ 
	 @Test
	public void MiniProject() throws Exception
	{
		    Scene scene = new Scene("Test scene");
	        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0,-1, 0)));
	        scene.set_distance(1000);
	        scene.set_background(new Color(102,178,255));
	        scene.set_ambientLight(new AmbientLight(new Color(255,255,255), 0.15));

	        scene.addGeometries(
	      
	                new Sphere(new Material(0.2, 0.2, 2, 0.5, 0.5),new Color(java.awt.Color.WHITE),new Point3D(0,-15,50),20),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.BLACK),new Point3D(8,-20,10),2.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.BLACK),new Point3D(-8,-20,10),2.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.WHITE),new Point3D(8,-20,0),0.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.WHITE),new Point3D(-8,-20,0),0.5),
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(0,-2,0),1),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(2,-3,0),0.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(-2,-3,0),0.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(-4,-4,0),0.6),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(4,-4,0),0.6),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(-6,-5,0),0.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(6,-5,0),0.5),
	                
	                new Sphere(new Material(0.5, 0.5, 30,0.5, 0.5),new Color(java.awt.Color.WHITE),new Point3D(-12,56,0),6),
	                new Sphere(new Material(0.5, 0.5, 30,0.5, 0.5),new Color(java.awt.Color.WHITE),new Point3D(12,56,0),6),
	                
	                new Polygon(new Material(0.5, 0.5, 30, 0.5, 0.5),new Color(153,76,0), new Point3D(15,25,60), new Point3D(15,20,60), new Point3D(43,5,60), new Point3D(43,10,60)),
	                new Polygon(new Material(0.5, 0.5, 30, 0.5, 0.5),new Color(153,76,0), new Point3D(35,9,60), new Point3D(38,9,60), new Point3D(38,2,60), new Point3D(35,2,60)),
	                
	                new Polygon(new Material(0.2, 0.2, 2, 0, 0),new Color(153,76,0), new Point3D(-15,25,60), new Point3D(-15,20,60), new Point3D(-43,5,60), new Point3D(-43,10,60)),
	                new Polygon(new Material(0.2, 0.2, 2, 0, 0),new Color(153,76,0), new Point3D(-35,9,60), new Point3D(-38,9,60), new Point3D(-38,2,60), new Point3D(-35,2,60)),
	                
	                new Polygon(new Material(0.2, 0.2, 2, 0, 0),new Color(0,0,0), new Point3D(8,-35,20), new Point3D(5,-43,20), new Point3D(-24,-26,20), new Point3D(-21,-18,20)),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(0,0,0),new Point3D(-22,-22.5,20),4),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(0,0,0),new Point3D(6.2,-39,20),4.2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(0,0,0),new Point3D(-15,-42,70),15),
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(70,-80,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(80,-60,20),1.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(70,-40,20),1.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(80,20,20),1.2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(70,0,20),1),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(80,20,20),0.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(70,40,20),0.4),
	             
	                
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(60,-90,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(50,-70,20),1.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(60,-50,20),1.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(50,30,20),1.2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(60,10,20),1),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(50,30,20),0.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(60,50,20),0.4),
	              
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(30,-80,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(40,-60,20),1.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(30,-40,20),1.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(40,-20,20),0.7),
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(20,-90,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(10,-70,20),1.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(20,-50,20),1.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(0,-80,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(0,-60,20),2),
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-70,-80,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-80,-60,20),1.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-70,-40,20),1.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-80,20,20),1.2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-70,0,20),1),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-80,20,20),0.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-70,40,20),0.4),
	             
	                
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-60,-90,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-50,-70,20),1.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-60,-50,20),1.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-50,30,20),1.2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-60,10,20),1),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-50,30,20),0.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0.5),new Color(255,255,255),new Point3D(-60,50,20),0.4),
	              
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(255,255,255),new Point3D(-30,-80,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(255,255,255),new Point3D(-40,-60,20),1.7),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(255,255,255),new Point3D(-30,-40,20),1.5),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(255,255,255),new Point3D(-40,-20,20),0.7),
	                
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(255,255,255),new Point3D(-20,-90,20),2),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(255,255,255),new Point3D(-10,-70,20),1.7),
	             
                    new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(255,128,0),new Point3D(0,-12,0),3),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.GREEN),new Point3D(0,30,0),3),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(0,15,0),3),
	                new Sphere(new Material(0.2, 0.2, 2, 0, 0),new Color(java.awt.Color.RED),new Point3D(0,45,0),3),
	                new Sphere(new Material(0.5, 0.5, 30,0.5, 0.5),new Color(java.awt.Color.WHITE),new Point3D(0,30,50),30));
	        
	        

	       
	        ImageWriter imageWriter = new ImageWriter("miniProject", 200, 200, 500, 500);
	        Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();
	        render._isSuperSampling=true;
	        render.renderImage();
	        render._imagewriter.writeToImage(); 
	}
	
}




