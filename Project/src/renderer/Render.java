package renderer;
import geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import Primitives.Color;
import Primitives.Material;
import Primitives.Ray;
import Primitives.Vector;
import Primitives.util;
import Primitives.Point3D;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import scene.Scene;

public class Render {
	// ...........
	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;

	/**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 *
	 */
	
	public Render setMultithreading(int threads) {
		if (threads < 0) throw new IllegalArgumentException("Multithreading must be 0 or higher");
		if (threads != 0) _threads = threads;
		else {
		int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
		_threads = cores <= 2 ? 1 : cores;
		}
		return this;
		}
	
	/**
	* Set debug printing on
	* @return the Render object itself
	*/
	public Render setDebugPrint() { _print = true; return this; }

	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */


		 private synchronized int nextP(Pixel target) {
		      ++col;
		      ++_counter;
		      if (col < _maxCols) {
		        target.row = this.row;
		        target.col = this.col;
		        if (_print && _counter == _nextCounter) {
		          ++_percents;
		          _nextCounter = _pixels * (_percents + 1) / 100;
		          return _percents;
		        }
		        return 0;
		      }
		      ++row;
		      if (row < _maxRows) {
		        col = 0;
		        target.row = this.row;
		        target.col = this.col;
		        if (_print && _counter == _nextCounter) {
		          ++_percents;
		          _nextCounter = _pixels * (_percents + 1) / 100;
		          return _percents;
		        }
		        return 0;
		      }
		      return -1;
		 }
		    

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}
	
//public class Render 
//{
	public ImageWriter _imagewriter;
	public Scene _scene;
	public boolean _isSuperSampling;
	
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	public Render(ImageWriter iw,Scene sc) 
	{
		_imagewriter= iw;
		_scene=sc;
		_isSuperSampling=true;
	}

	
	/*public void renderImage() throws Exception
	{
		Camera camera = _scene.get_camera();
		Intersectable geometries = _scene.get_geometries();
		Color background = _scene.get_background();
		int nX = _imagewriter.getNx();
        int nY = _imagewriter.getNy();
        double distance=_scene.get_distance();
        double width= _imagewriter.getWidth();//30
        double height= _imagewriter.getHeight();//30

	


        Color color;
        Color middle=new Color(0,0,0);
        int i,j;
        GeoPoint closestPoint=new GeoPoint (null,new Point3D(0,0,1) );
        double Ry=height/nY;//10
		double Rx=width/nX;//10
		List<Color> colors=new ArrayList<Color>();
		
		
		
		
		
		
		
        for ( j = 0; j < nY; j++) //0-2
        {
            for ( i = 0; i < nX; i++) //0-2
            {
            	if(_isSuperSampling==false)
            	{
            	 	Ray ray = camera.constructRayThroughPixel(nX, nY, i, j, distance, width, height);
                	List<GeoPoint> intersectionPoints = geometries.findIntsersections(ray);
                    if(intersectionPoints.isEmpty())
                    	_imagewriter.writePixel(i, j, background.getColor());
                    else
                    {
                    	 //closestPoint =  getClosestPoint(intersectionPoints);
                    	  closestPoint = findCLosestIntersection(ray);
                         _imagewriter.writePixel(i, j, closestPoint == null ? _scene.get_background().getColor(): calcColor(closestPoint, ray).getColor());
                    }
            	}
            	else
            	{
            		
            		Point3D _pc=new Point3D(_scene.get_camera().get_location().add(_scene.get_camera().get_Vto().scale(distance)));
            		double Yi= (i - (nY-1)/2d)*Ry;
            		double Xj= (j -(nX-1)/2d)*Rx ;
            		Point3D Pij=_pc;
            		if (Xj!= 0 ) 
            		     Pij=Pij.add(_scene.get_camera().get_Vright().scale(Xj));
            		if (Yi!= 0 ) 
            		     Pij=Pij.add(_scene.get_camera().get_Vup().scale(-Yi));
            		
            		Color pixelColor=new Color(recursive(nX, nY, Pij, distance,Rx,Ry,1));
            		_imagewriter.writePixel(i, j, pixelColor.getColor());
            	}
            	
            	 
        }
        }
     	}   */
	
	
	public void renderImage() throws Exception
	 /* improve 2*/
	 
	{
		Camera camera = _scene.get_camera();
		Intersectable geometries = _scene.get_geometries();
		Color background = _scene.get_background();
		int nX = _imagewriter.getNx();//3
        int nY = _imagewriter.getNy();//3
        double distance=_scene.get_distance();
        double width= _imagewriter.getWidth();//30
        double height= _imagewriter.getHeight();//30

   
        double Ry=height/nY;//10
		double Rx=width/nX;//10
		
	    final Pixel thePixel = new Pixel(nY, nX); // Main pixel management object
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) { // create all threads
        threads[i] = new Thread(() ->
        {
        Pixel pixel = new Pixel(); // Auxiliary thread’s pixel object
        while (thePixel.nextPixel(pixel)) 
        {
        
      
       // _imagewriter.writePixel(pixel.col, pixel.row, calcColor(ray).getColor());
        
        
       
        try {
		
       
            	if(_isSuperSampling==false)
            	{
            	 	Ray ray = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, distance, width, height);
                	List<GeoPoint> intersectionPoints = geometries.findIntsersections(ray);
                    if(intersectionPoints.isEmpty())
                    	_imagewriter.writePixel(pixel.col, pixel.row, background.getColor());
                    else
                    {
                         _imagewriter.writePixel(pixel.col, pixel.row, findCLosestIntersection(ray) == null ? _scene.get_background().getColor(): calcColor(findCLosestIntersection(ray), ray).getColor());
                    }
            	}
            	else
            	{            		
            		Point3D _pc=new Point3D(_scene.get_camera().get_location().add(_scene.get_camera().get_Vto().scale(distance)));
            		double Yi= (pixel.col- (nY-1)/2d)*Ry;
            		double Xj= (pixel.row -(nX-1)/2d)*Rx ;
            		Point3D Pij=_pc;
            		if (Xj!= 0 ) 
            		     Pij=Pij.add(_scene.get_camera().get_Vright().scale(Xj));
            		if (Yi!= 0 ) 
            		     Pij=Pij.add(_scene.get_camera().get_Vup().scale(-Yi));
            		
            		Color pixelColor=new Color(recursive(nX, nY, Pij, distance,Rx,Ry,0));
            		_imagewriter.writePixel(pixel.row, pixel.col, pixelColor.getColor());
            	}
        }catch(Exception e) {}
        }
        });
        
	}
            	 // Start threads

                for (Thread thread : threads) thread.start();



                // Wait for all threads to finish

                for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}

                if (_print) System.out.printf("\r100%%\n");
     	}   
	
	

	/*
	 * check if the colors are equal-help function
	 */
	private boolean isColorsEqual(List<Color> colors)
	{

		if(colors.size()>=4)
		{
		if(colors.get(0).equals(colors.get(1))&&colors.get(0).equals(colors.get(2))&&colors.get(0).equals(colors.get(2))&&colors.get(0).equals(colors.get(3)))
			return true;
		}
		return false;
		
	}
	/*
	 * render image-uses construct ray throw pixel to calculate the color of each pixel in the image 
	 * improve 1
	 */
  /*  public void renderImage() throws Exception
	{
		Camera camera = _scene.get_camera();
		Intersectable geometries = _scene.get_geometries();
		Color background = _scene.get_background();
		int nX = _imagewriter.getNx();//3
        int nY = _imagewriter.getNy();//3
        double distance=_scene.get_distance();
        double width= _imagewriter.getWidth();//30
        double height= _imagewriter.getHeight();//30
        Color color;
        Color middle=new Color(0,0,0);
        int i,j;
        GeoPoint closestPoint=new GeoPoint (null,new Point3D(0,0,1) );
        double Ry=height/nY;//10
		double Rx=width/nX;//10
        for ( j = 0; j < nY; j++) //0-2
        {
            for ( i = 0; i < nX; i++) //0-2
            {
            	if(_isSuperSampling==false)
            	{
            	 	Ray ray = camera.constructRayThroughPixel(nX, nY, i, j, distance, width, height);
                	List<GeoPoint> intersectionPoints = geometries.findIntsersections(ray);
                    if(intersectionPoints.isEmpty())
                    	_imagewriter.writePixel(i, j, background.getColor());
                    else
                    {
                    	 //closestPoint =  getClosestPoint(intersectionPoints);
                    	  closestPoint = findCLosestIntersection(ray);
                         _imagewriter.writePixel(i, j, closestPoint == null ? _scene.get_background().getColor(): calcColor(closestPoint, ray).getColor());
                    }
            	}
            	else
            	{
           color=new Color(0,0,0);//reset the color each iteration
            	for(int k=0;k<9;k++)//0-8
            	{	
            		for(int w=0;w<9;w++)//0-8
            		{	
            			
            			Ray ray = camera.constructRayThroughPixel(9*nX, 9*nY,w+i*9,k+j*9, distance, width, height);
            				//closestPoint =  getClosestPoint(intersectionPoints);//-before changes
           					closestPoint = findCLosestIntersection(ray);
           					
                            if(closestPoint == null)
                            {
                            	if(w==5&&k==5)
                            		middle=background;
                            	else
                                   color= color.add(background); 
                            }
                            else
                            {
                            	if(w==5&&k==5)
                            		middle=calcColor(closestPoint, ray);
                            	else
                                  color=color.add(calcColor(closestPoint, ray));//add to the color of the pixel  
                            }
            		}
            	}
            	color=(color.reduce(80)).scale(0.7);
            	color=color.add(middle.scale(0.3));
        		_imagewriter.writePixel(i, j,color.getColor());
        		color=new Color(0,0,0);//reset the color each iteration
            }
     		}
        }
     	}   */ 
	
/*
 * return ray list from the pixel's vertices
 */
	public List<Ray> constructRays(Point3D center,double Rx,double Ry) throws Exception
	{
		List<Ray> vertices=new ArrayList<Ray>();
       Point3D location=_scene.get_camera().get_location();
		
		
		Point3D temp=new Point3D(center.getX().get()+Rx/2, center.getY().get()+Ry/2, center.getZ().get());
		vertices.add(new Ray(location,temp.substract(location)));
		
		temp=new Point3D(center.getX().get()+Rx/2, center.getY().get()-Ry/2, center.getZ().get());
		vertices.add(new Ray(location,temp.substract(location)));

		temp=new Point3D(center.getX().get()-Rx/2, center.getY().get()+Ry/2, center.getZ().get());
		vertices.add(new Ray(location,temp.substract(location)));

		temp=new Point3D(center.getX().get()-Rx/2, center.getY().get()-Ry/2, center.getZ().get());
		vertices.add(new Ray(location,temp.substract(location)));

        return vertices;
	}
    
    private Color recursive(int nX, int nY, Point3D center, double screenDistance, double Rx, double Ry,int number) throws Exception 
    {
    	Color avg=new Color(0,0,0);
    	List<Ray> rays=constructRays(center,Rx,Ry);
    	List<Color> colors=new ArrayList<Color>();
        GeoPoint closestPoint;//=new GeoPoint (null,new Point3D(0,0,1) );

    	for  (Ray ray :rays) 
    	{
    		List<GeoPoint> intersectionPoints = _scene.get_geometries().findIntsersections(ray);
            if(intersectionPoints.isEmpty())
            	colors.add( _scene.get_background());
            else
            {
            	  closestPoint = findCLosestIntersection(ray);
                  colors.add( closestPoint == null ? _scene.get_background(): calcColor(closestPoint, ray));
            }
    	}
    	
    	if(number<=4)
    	{
    		
    	if(isColorsEqual(colors))//if all the vertices equal
    	{
    		return colors.get(0);

    	}
    	
    	
    	if(!isColorsEqual(colors))//otherwise
    	{
    	Point3D center1=new Point3D(center.getX().get()+Rx/4,center.getY().get()+Ry/4,center.getZ().get());
    	Point3D center2=new Point3D(center.getX().get()-Rx/4,center.getY().get()+Ry/4,center.getZ().get());
    	Point3D center3=new Point3D(center.getX().get()+Rx/4,center.getY().get()-Ry/4,center.getZ().get());
    	Point3D center4=new Point3D(center.getX().get()-Rx/4,center.getY().get()-Ry/4,center.getZ().get());
        
    	List<Point3D>pointList=List.of(center1,center2,center3,center4);
    	//Color temp=new Color(0,0,0);
    	//for(Color color: colors)//keep the average of the previous color
		//	temp=temp.add(color);
		//temp= temp.reduce(4);
	    List <Color> newColors=new ArrayList<Color>();
	    for (Point3D point : pointList) 
	    	newColors.add(recursive(nX,nY,point,screenDistance,Rx/2,Ry/2,number+1));
	    
	    
	    for(Color color: newColors)
	    	avg=avg.add(color);
	    
	   // avg=avg.add(temp);
	    
	    avg=avg.reduce(4);

	    return avg;
		
    		
    	}
    
    	}
    	avg=new Color(0,0,0);
    		for(Color color: colors)//in case that the recursive level
    			avg=avg.add(color);
    		avg=avg.reduce(4);
    		return avg;

    }

    
     	     	 
	
	
	/*
	 * return the closest intersection point
	 */
	
	 public GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) 
	{
	    double minimum=_scene.get_camera().get_location().distance(intersectionPoints.get(0).point);
	    double minDistance;
	    GeoPoint Pmin=new GeoPoint(intersectionPoints.get(0).geometry,intersectionPoints.get(0).point);
		for (int i = 1; i < intersectionPoints.size(); i++)
		{
			minDistance=_scene.get_camera().get_location().distance(intersectionPoints.get(i).point);
			if(minimum>minDistance)
			{
				minimum=minDistance;
			    Pmin=intersectionPoints.get(i);
			}
		}
		return Pmin; 
	}
	 
	 /*
		 * return the closest intersection point from the ray
		 */
		private GeoPoint findCLosestIntersection(Ray ray) throws Exception
		{
			if(ray==null)
				return null;
			List<GeoPoint> intersectionPoints=_scene.get_geometries().findIntsersections(ray);
			  if (intersectionPoints.isEmpty())
		            return null;
		    double minimum=Double.MAX_VALUE;;//=ray.getPoint().distance(intersectionPoints.get(0).point);
		    double minDistance;
		    GeoPoint Pmin=null;
			for (int i = 0; i < intersectionPoints.size(); i++)//find the closest point from the list
			{
				minDistance=ray.getPoint().distance(intersectionPoints.get(i).point);
				if(minimum>minDistance)
				{
					minimum=minDistance;
				    Pmin=intersectionPoints.get(i);
				}
			}
			return Pmin; 	
		}
		
		
    /*
    * return the color with the landing factor
    */
	 private Color calcColor(GeoPoint point,Ray inRay, int level, double k) throws Exception
	 {
		 Color color = point.geometry.get_emmission();
		 Vector v = point.point.substract(_scene.get_camera().get_location()).normalized();
		 Vector n = point.geometry.getNormal(point.point).normalized();
		 Material material =point.geometry.get_material();
		 int nShiness = material.get_nShiness();
		 double kd = material.get_kd();
		 double ks = material.get_ks();
		 List<LightSource> lights=_scene.get_lights();
		 if(lights!=null)
		 {
		   for (LightSource lightSource : lights)
		   {
			 Vector l = lightSource.getL(point.point).normalized();
			 double nl = util.alignZero(n.dotProduct(l));
          
			 if (((n.dotProduct(l))>0 && (n.dotProduct(v))>0)  || ((n.dotProduct(l))<0 && (n.dotProduct(v))<0) )
			 {
				 double ktr = transparency(lightSource, l, n, point);
				 if (ktr * k > MIN_CALC_COLOR_K) 
				 {	 
			       Color lightIntensity = lightSource.getIntensity(point.point).scale(ktr);
			       color = color.add(calcDiffusive(kd, nl, lightIntensity),calcSpecular(ks, l, n, v,nl, nShiness, lightIntensity));
				 }
		     }
           }
		 }
		 if (level == 1)
			 return Color.BLACK;
		 double kr = point.geometry.get_material().get_kr();
		 double kkr = k * kr;
		 if (kkr > MIN_CALC_COLOR_K) //reflection
		 {
		   Ray reflectedRay = calcReflectedRay(n, point.point, inRay);
		   GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay);
		   if (reflectedPoint != null)
		      color = color.add(calcColor(reflectedPoint, reflectedRay,level-1, kkr).scale(kr));
		 }
		 double kt = point.geometry.get_material().get_kt();
		 double kkt = k * kt;
		 if (kkt > MIN_CALC_COLOR_K) //refraction
		 {
		   Ray refractedRay = calcRefractedRay(n,inRay,point.point) ;
		   GeoPoint refractedPoint = findCLosestIntersection(refractedRay);
		   if (refractedPoint != null)
		      color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt));
		 }
		 return color;
	 }
	
	 /*
	  * call recursively to calcColor
	  */
	 private Color calcColor(GeoPoint gp, Ray ray) throws Exception
	 {
		 return calcColor(findCLosestIntersection(ray), ray, MAX_CALC_COLOR_LEVEL, 1.0).add(
				 _scene.get_ambientLight().get_intensity());
	 }

	 /*
	  * calculate the diffuse factor
	  */
	 private Color calcDiffusive(double kd,double nl,Color lightIntensity)
	 {
		 if (nl < 0)
			 nl = -nl;
		 return lightIntensity.scale(nl*kd);
	 }
	 
	 /*
	  * calculate the specular factor
	  */
	 private Color calcSpecular(double ks,Vector l,Vector n,Vector v,double nl,int shine, Color lightIntensity) throws Exception
	 {
		 double s=shine;
		 Vector R = l.add(n.scale(-2 * nl)).normalized();
		    double minusVR = -util.alignZero(R.dotProduct(v));
	        if (minusVR <= 0) 
	        {
	            return Color.BLACK; // view from direction opposite to r vector
	        }
		 return lightIntensity.scale(ks * Math.pow(minusVR, s));
	}
	 
	 /*
	  * print the grid
	  */
	public void printGrid(int interval, java.awt.Color color)
	{
		for (int j = 0; j < _imagewriter.getNy(); j++) 
        {
            for (int i = 0; i < _imagewriter.getNx(); i++) 
            {
            	if(i%interval==0||j%interval==0)
            		_imagewriter.writePixel(i, j, color);	
            }
        }
		_imagewriter.writeToImage();
     }
	
	/*
	 * check if the point has shadow
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) throws Exception
	{
		Vector lightDirection = l.scale(-1).normalized(); // from point to light source
		Ray lightRay = new Ray(gp.point, lightDirection,n);
		List<GeoPoint> intersections = _scene.get_geometries().findIntsersections(lightRay);
		if (intersections.isEmpty()) 
			return true;
		double lightDistance = light.getDistance(gp.point);
		for (GeoPoint geo : intersections) {
		   if (util.alignZero(geo.point.distance(geo.point)-lightDistance) <= 0 && gp.geometry.get_material().get_kt() == 0)
		       return false;
		}
		return true;
	}
	
	/*
	 * unshaded refactoring for geometries with transparency
	 */
	private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geopoint) throws Exception
	{
		Vector lightDirection = l.scale(-1).normalized(); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection, n);
		List<GeoPoint> intersections = _scene.get_geometries().findIntsersections(lightRay);
		if (intersections.isEmpty()) 
			return 1.0;
		double lightDistance = ls.getDistance(geopoint.point);
		double ktr = 1.0;
		for (GeoPoint gp : intersections) {
		if (util.alignZero(gp.point.distance(geopoint.point)- lightDistance) <= 0) {
		ktr *= gp.geometry.get_material().get_kt();
		if (ktr < MIN_CALC_COLOR_K) 
			return 0.0;
		}
		}
		return ktr;
	}
	
	/*
	 * calculate the reflected ray
	 */
	private Ray calcReflectedRay(Vector n,Point3D point,Ray ray) throws Exception
	{
		Vector v=new Vector(ray.getDirection()).normalized();
		double vn=v.dotProduct(n);
		if(vn==0)
			return null;
		Vector r= v.substract(n.scale(2*vn)).normalized();
		return new Ray(point, r,n);
	}
	
	/*
	 * calculate the refracted ray
	 */
	private Ray calcRefractedRay(Vector n,Ray ray, Point3D point) throws Exception
	{
		return new Ray(point, ray.getDirection(),n);
	}
	
	

	
	 

}
