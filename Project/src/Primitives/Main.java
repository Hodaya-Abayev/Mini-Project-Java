//Hodaya Abayev 322758889
//Esti Masas 207646472
package Primitives;

import Primitives.*;
import geometries.Sphere;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static Primitives.util.*;

public final class Main {

    /**
     * Main program to tests initial functionality of the 1st stage
     * 
     * @param args irrelevant here
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
    	  Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

          // ============ Equivalence Partitions Tests ==============

          // TC01: Ray's line is outside the sphere (0 points)
         assertEquals("Ray's line out of sphere", null,
                         sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

    	
    	
    	
//        try { // test zero vector
//            new Vector(0, 0, 0);
//            System.out.println("ERROR: zero vector does not throw an exception");
//        } catch (Exception e) {}
//
//        Vector v1 = new Vector(1, 2, 3);
//        Vector v2 = new Vector(-2, -4, -6);
//        Vector v3 = new Vector(0, 3, -2);
//
//        // test length..
//        if (!isZero(v1.lengthSquared() - 14))
//        	System.out.println("ERROR: lengthSquared() wrong value");
//        if (!isZero(new Vector(0, 3, 4).length() - 5))
//        	System.out.println("ERROR: length() wrong value");
//
//        // test Dot-Product
//        if (!isZero(v1.dotProduct(v3)))
//        	System. out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
//        if (!isZero(v1.dotProduct(v2) + 28))
//        	System.out.println("ERROR: dotProduct() wrong value");
//
//        // test Cross-Product
//       try { // test zero vector
//            v1.crossProduct(v2);
//            System.out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
//        } catch (Exception e) {}
//        Vector vr = v1.crossProduct(v3);
//        if (!isZero(vr.length() - v1.length() * v3.length()))
//        	System.out.println("ERROR: crossProduct() wrong result length");
//        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
//        	System.out.println("ERROR: crossProduct() result is not orthogonal to its operands");
//
//        // test vector normalization vs vector length and cross-product
//        Vector v = new Vector(1, 2, 3);
//        Vector vCopy = new Vector(v);
//        Vector vCopyNormalize = vCopy.normalize();
//        if (vCopy != vCopyNormalize)
//        	System.out.println("ERROR: normalize() function creates a new vector");
//        if (!isZero(vCopyNormalize.length() - 1))
//        	System.out.println("ERROR: normalize() result is not a unit vector");
//        Vector u = v.normalized();
//        if (u == v)
//        	System.out.println("ERROR: normalizated() function does not create a new vector");
//
//        // Test operations with points and vectors
//        Point3D p1 = new Point3D(1, 2, 3); 
//        if (!Point3D.zero.equals(p1.add(new Vector(-1, -2, -3))))
//        	System.out.println("ERROR: Point + Vector does not work correctly");
//        if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).substract(p1)))
//        	System.out.println("ERROR: Point - Point does not work correctly");

        System.out.println("If there were no any other outputs - all tests succeeded!");
    }
}