package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import Primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering abasic image
 * 
 * 
 */
public class LightTests {

    /**
     * Produce a picture of a sphere lighted by a directional light
     * @throws Exception 
     */


	
    @Test
    public void sphereDirectional() throws Exception {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries( new Sphere(new Material(0.5, 0.5, 100),new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50), 50));

        scene.addLights(new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));
        //scene.addLights(new SpotLight( new Vector(1, -1, 2),new Color(500, 300, 0), new Point3D(-50, 50, -50),  1, 0.00001, 0.00000001));

        ImageWriter imageWriter = new ImageWriter("sphereDirectional", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render._imagewriter.writeToImage();
    }
 
    /**
     * Produce a picture of a sphere lighted by a point light
     * @throws Exception 
     */
 
   @Test
    public void spherePoint() throws Exception {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere( new Material(0.5, 0.5, 100),new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50),50));

        scene.addLights(new PointLight(new Color(500, 300, 0), new Point3D(-50, 50, -50), 1, 0.00001, 0.000001));

        ImageWriter imageWriter = new ImageWriter("spherePoint", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render._imagewriter.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     * @throws Exception 
     */
    
    @Test
    public void sphereSpot() throws Exception {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Material(0.5, 0.5, 100),new Color(0,0,255),  new Point3D(0, 0, 50),50));

        scene.addLights(new SpotLight( new Vector(1, -1, 2),new Color(500, 300, 0), new Point3D(-50, 50, -50),
               1, 0.00001, 0.00000001));

        ImageWriter imageWriter = new ImageWriter("sphereSpot", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);
        
        
        render.renderImage();
        render._imagewriter.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a directional light
     * @throws Exception 
     */
  
  
    @Test
    public void trianglesDirectional() throws Exception {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle( new Material(0.8, 0.2, 300),Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(new Material(0.8, 0.2, 300),Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("trianglesDirectional", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render._imagewriter.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a point light
     * @throws Exception 
     */
  
    @Test
    public void trianglesPoint() throws Exception {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle( new Material(0.5, 0.5, 300),Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle( new Material(0.5, 0.5, 300),Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new PointLight(new Color(500, 250, 250),
                new Point3D(10, 10, 130),
                1, 0.0005, 0.0005));

        ImageWriter imageWriter = new ImageWriter("trianglesPoint", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render._imagewriter.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light
     * @throws Exception 
     */
    
    @Test
    public void trianglesSpot() throws Exception {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle( new Material(0.5, 0.5, 300),Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(new Material(0.5, 0.5, 300),Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));

        scene.addLights(new SpotLight( new Vector(-2, 2, 1),new Color(500, 250, 250),
                new Point3D(10, 10, 130),
                1, 0.0001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("trianglesSpot", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render._imagewriter.writeToImage();
    }
  
	  /**
     * Produce a picture of a triangles lighted by a point light ,spot light and directional light
     */
	
    @Test
    public void trianglesMultiLight() throws Exception 
    {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle( new Material(0.5, 0.5, 300),Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(150, 150, 150), new Point3D(75, -75, 150)),
                new Triangle(new Material(0.5, 0.5, 300),Color.BLACK,
                        new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150)));
        DirectionalLight directionLight=new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1));
        PointLight pointLight=new PointLight(new Color(500, 250, 250),new Point3D(40, 40, 130),1, 0.0005, 0.0005);
        SpotLight spotLight=new SpotLight( new Vector(-2, 2, 1),new Color(500, 250, 250), new Point3D(-15, -15, 130),    1, 0.0001, 0.000005);
        scene.addLights(spotLight,pointLight,directionLight);
        ImageWriter imageWriter = new ImageWriter("trianglesMultiLights", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render._imagewriter.writeToImage();
    }
   
    /**
     * Produce a picture of a sphere lighted by a point light ,spot light and directional light
     * @throws Exception 
     */
  
    @Test
    public void sphereMultiLights() throws Exception {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere( new Material(0.5, 0.5, 100),new Color(java.awt.Color.BLUE), new Point3D(0, 0, 50),50));

        DirectionalLight directionLight=new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1));
        PointLight pointLight=new PointLight(new Color(500, 300, 0), new Point3D(-50, 50, -50), 1, 0.00001, 0.000001);
        SpotLight spotLight=new SpotLight( new Vector(1, -1, 2),new Color(500, 300, 0), new Point3D(-50, 50, -50),  1, 0.00001, 0.00000001);
        scene.addLights(spotLight,pointLight,directionLight);

        ImageWriter imageWriter = new ImageWriter("sphereMultiPoint", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render._imagewriter.writeToImage();
    }
 

}
