/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import Primitives.Vector;

import Primitives.util;

/**
 * @author осас
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link Primitives.Vector#substract(Primitives.Vector)}.
	 * @throws Exception 
	 */
	@Test
	//This test checks if the substract function is right
	public void testSubstract() throws Exception {
		
		  Vector v1 = new Vector(1, 2, 3);
	      Vector v2 = new Vector(-2, -4, -6);
	      Vector vr = v1.substract(v2);
	     
	      Vector v3 = new Vector(3, 6, 9);
	     assertEquals("substract() wrong result", v3, vr); 
	}

	/**
	 * Test method for {@link Primitives.Vector#add(Primitives.Vector)}.
	 * @throws Exception 
	 */
	@Test
	//This test checks if the add function is right
	public void testAdd() throws Exception {
		  Vector v1 = new Vector(1, 2, 3);
	      Vector v2 = new Vector(-2, -4, -6);
	      Vector vr = v1.add(v2);
	      Vector v3 = new Vector(-1,-2,-3);
	     assertEquals("add() wrong result", v3, vr); 
	}

	/**
	 * Test method for {@link Primitives.Vector#scale(double)}.
	 * @throws Exception 
	 */
	@Test
	//This test checks if the Scale function is right
	public void testScale() throws Exception {
		Vector v1 = new Vector(1, 2, 3);
		Vector vr = v1.scale(2);
		Vector v2=new Vector(2,4,6);
		assertEquals("Scale() wrong result", v2, vr); 
	}

	/**
	 * Test method for {@link Primitives.Vector#crossProduct(Primitives.Vector)}.
	 * @throws Exception 
	 */
	@Test
	// test if the TestProduct function is right
		public void testCrossProduct() throws Exception {
	        Vector v1 = new Vector(1, 2, 3);
	        Vector v2 = new Vector(-2, -4, -6);

	        // ============ Equivalence Partitions Tests ==============
	        Vector v3 = new Vector(0, 3, -2);
	        Vector vr = v1.crossProduct(v3);

	        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
	        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

	        // Test cross-product result orthogonality to its operands
	        assertTrue("crossProduct() result is not orthogonal to 1st operand", util.isZero(vr.dotProduct(v1)));
	        assertTrue("crossProduct() result is not orthogonal to 2nd operand", util.isZero(vr.dotProduct(v3)));

	        // =============== Boundary Values Tests ==================
	        // test zero vector from cross-productof co-lined vectors
	        try {
	            v1.crossProduct(v2);
	            fail("crossProduct() for parallel vectors does not throw an exception");
	        } catch (Exception e) {}
	    }



	/**
	 * Test method for {@link Primitives.Vector#dotProduct(Primitives.Vector)}.
	 * @throws Exception 
	 */
	@Test
	// test if the DotProduct function is right
	public void testDotProduct() throws Exception
	{
		 Vector v1 = new Vector(1, 2, 3);
	     Vector v2 = new Vector(-2, -4, -6);
	     Vector v3 = new Vector(0, 3, -2);
	     
	     assertEquals("",v1.dotProduct(v3),0,1e-10);
	     assertEquals("",v1.dotProduct(v2),-28,1e-10);
	}

	/**
	 * Test method for {@link Primitives.Vector#lengthSquared()}.
	 * @throws Exception 
	 */
	@Test
	// test if the LengthSquared function is right
	public void testLengthSquared() throws Exception
	{
		Vector v = new Vector(1,2,2);
		double l= 9;
		assertEquals("", l, v.lengthSquared(),1e-10);
	}

	/**
	 * Test method for {@link Primitives.Vector#length()}.
	 * @throws Exception 
	 */
	@Test
	// test if the Length function is right
	public void testLength() throws Exception
	{
		Vector v = new Vector(1,2,2);
		double l= 3;
		assertEquals("", l, v.length(),1e-10);
	}

	/**
	 * Test method for {@link Primitives.Vector#normalize()}.
	 * @throws Exception 
	 */
	@Test
	// test if the Normalize function is right
	public void testNormalize() throws Exception
	{
		Vector v = new Vector(3.5,-5,10);
		v.normalize();
		assertEquals("", 1, v.length(),1e-10);
	}

	/**
	 * Test method for {@link Primitives.Vector#normalized()}.
	 * @throws Exception 
	 */
	@Test
	// test if the Normalized function is right
	public void testNormalized() throws Exception
	{
		Vector v = new Vector(3.5,-5,10);
		Vector v1=new Vector(v.normalized());
		assertEquals("", 1, v1.length(),1e-10);
	}

}

