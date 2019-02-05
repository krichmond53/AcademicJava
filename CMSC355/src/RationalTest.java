/*
 * Kevin Richmond
 * CMSC 355 
 * Spring 2017
 * Assignment 3 - jUnit testing
 * 
 * This assignment is for practice using jUnit and developing Test-Drive Design
 * habits for future design projects.
 * 
 * The rational class creates fraction objects and then performs basic mathematics
 * operations on them to return the proper rational number (faction).
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class RationalTest {
	Rational r1 = new Rational();
	
	
	@Test
	public void testSetNum(){ // positive num
		r1.setNum(4);
		int n = r1.getNum();
		
		assertTrue(n==4);
		System.out.println(r1.toString());
	}
	
	@Test
	public void testSetNum1(){ // negative num
		r1.setNum(-4);
		int n = r1.getNum();
		
		assertTrue(n==-4);
		System.out.println(r1.toString());
	}

	@Test
	public void testSetNum3(){ // num = 0
		r1.setNum(0);
		int n = r1.getNum();
		
		assertTrue(n==0);
		System.out.println(r1.toString());
	}
	
	@Test
	public void testSetDen(){ // Set to positive denom
		r1.setDen(4);
		int d = r1.getDen();
		
		assertTrue(d==4);
		System.out.println(r1.toString());
	}
	
	@Test
	public void testSetDen1(){ // Set to negative denom
		r1.setDen(-4);
		int d = r1.getDen();
		
		assertTrue(d==4);
		System.out.println(r1.toString());
	}
	
	@Test (expected = Exception.class)//IllegalArgumentException.class)
	public void testSetDen2(){ // Set denom to 0
		r1.setDen(0);
		System.out.println(r1.toString());
	}
	
	@Test
	public void testConst(){ // default constructor 
		Rational test = new Rational();
		int n = test.getNum();
		int d = test.getDen();
				
		assertTrue(n == 1);
		assertTrue(d == 1);
	}
	
	@Test
	public void testConst1(){ // two positive integers 
		Rational test = new Rational(1, 2);
		int n = test.getNum();
		int d = test.getDen();
				
		assertTrue(n > 0);
		assertTrue(d > 0);
	}

	@Test
	public void testConst2(){ // two negative integers 
		Rational test = new Rational(-1, -2);
		int n = test.getNum();
		int d = test.getDen();
				
		assertTrue(n > 0);
		assertTrue(d > 0);
	}

	@Test
	public void testConst3(){ // positive num, negative den 
		Rational test = new Rational(1, -2);
		int n = test.getNum();
		int d = test.getDen();
				
		assertTrue(n < 0);
		assertTrue(d > 0);		
	}

	@Test
	public void testConst4(){ // negative num, positive den 
		Rational test = new Rational(-1, 2);
		int n = test.getNum();
		int d = test.getDen();
				
		assertTrue(n < 0);
		assertTrue(d > 0);
	}

	@Test
	public void testConst5(){ // num = 0, positive den 
		Rational test = new Rational(0, 2);
		int n = test.getNum();
		int d = test.getDen();
				
		assertTrue(n == 0);
		assertTrue(d > 0);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConst6(){ // positive num, den = 0 
		@SuppressWarnings("unused")
		Rational test = new Rational(1, 0);
	}
	
	@Test
	public void testEquals(){ // See if two fractions are equal
		Boolean eq=false;
		
		Rational r1 = new Rational(1, 1);
		Rational r2 = new Rational(1, 1);
		eq = r1.equals(r2);
		
		assertTrue(eq == true);
	}
	
	@Test
	public void testEquals1(){ // See if two fractions are not equal
		
		Rational r1 = new Rational(2, 1);
		Rational r2 = new Rational(1, 2);
		Boolean eq = r1.equals(r2);
		
		assertTrue(eq == false);
	}
	
	@Test
	public void testAdd1(){ // Add two positive fractions
		Rational r1 = new Rational(4, 6);
		Rational r2 = new Rational(1, 3);
		r1.add(r2);
		int n = r1.getNum();
		int d = r1.getDen();
				
		assertTrue(n == 18);
		assertTrue(d == 18);
	}
	
	@Test
	public void testAdd2(){ // Add two negative fractions
		Rational r1 = new Rational(1, -6);
		Rational r2 = new Rational(-2, 3);
		r1.add(r2);
		int n = r1.getNum();
		int d = r1.getDen();
				
		assertTrue(n == -15);
		assertTrue(d == 18);
	}
	
	@Test
	public void testAdd3(){ // Add one positive and one negative
		Rational r1 = new Rational(-1, 6);
		Rational r2 = new Rational(2, 3);
		r1.add(r2);
		int n = r1.getNum();
		int d = r1.getDen();
				
		assertTrue(n == 9);
		assertTrue(d == 18);
	}
	
	@Test
	public void testAdd4(){ // Add one negative and one positive
		Rational r1 = new Rational(1, 6);
		Rational r2 = new Rational(2, -3);
		r1.add(r2);
		int n = r1.getNum();
		int d = r1.getDen();
				
		assertTrue(n == -9);
		assertTrue(d == 18);
	}

	@Test
	public void testSubtract(){ // Subtract two positive fractions
		Rational r1 = new Rational(3, 6);
		Rational r2 = new Rational(1, 6);
		r1.subtract(r2);
		Rational r3 = new Rational(2,6);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	}

	@Test
	public void testSubtract1(){ // Subtract two negative fractions
		Rational r1 = new Rational(3, -6);
		Rational r2 = new Rational(-1, 6);
		r1.subtract(r2);
		Rational r3 = new Rational(-2,6);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	}

	@Test
	public void testSubtract2(){ // Subtract one positive one neg
		Rational r1 = new Rational(3, 6);
		Rational r2 = new Rational(-1, 6);
		r1.subtract(r2);
		
		Rational r3 = new Rational(4,6);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);
	}

	@Test
	public void testSubtract3(){ // Subtract one positive one neg
		Rational r1 = new Rational(-3, 6);
		Rational r2 = new Rational(1, 6);
		r1.subtract(r2);
		
		Rational r3 = new Rational(-4,6);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);
	}
	
	@Test
	public void testMult(){ // Multiply 2 positives 
		Rational r1 = new Rational(2, 7);
		Rational r2 = new Rational(3, 4);
		r1.multiply(r2);
		Rational r3 = new Rational(6,28);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}

	@Test
	public void testMult2(){ // Multiply 2 negatives 
		Rational r1 = new Rational(2, -7);
		Rational r2 = new Rational(3, -4);
		r1.multiply(r2);
		Rational r3 = new Rational(6,28);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}

	@Test
	public void testMult3(){ // Multiply 1 neg/1 pos 
		Rational r1 = new Rational(-2, 7);
		Rational r2 = new Rational(3, 4);
		r1.multiply(r2);
		Rational r3 = new Rational(-6,28);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}

	@Test
	public void testMult1(){ // Multiply 1 pos/1 neg
		Rational r1 = new Rational(2, 7);
		Rational r2 = new Rational(3, -4);
		r1.multiply(r2);
		Rational r3 = new Rational(6,-28);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}

	@Test
	public void testMult0(){ // Multiply 1 pos/1 neg
		Rational r1 = new Rational(2, 7);
		Rational r2 = new Rational(0, 1);
		r1.multiply(r2);
		Rational r3 = new Rational(0,7);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}
	
	@Test
	public void testDiv(){ // Divide 2 pos 
		Rational r1 = new Rational(2, 7);
		Rational r2 = new Rational(1, 4);
		r1.divide(r2);
		Rational r3 = new Rational(8,7);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}

	@Test
	public void testDiv1(){ // Divide 2 neg
		Rational r1 = new Rational(2, -7);
		Rational r2 = new Rational(-1, 4);
		r1.divide(r2);
		Rational r3 = new Rational(8,7);
		Boolean eq = r1.equals(r3);
		assertTrue(eq == true);	
	}

	@Test
	public void testDiv2(){ // Divide 1 neg, 1 pos 
		Rational r1 = new Rational(2, -7);
		Rational r2 = new Rational(1, 4);
		r1.divide(r2);
		Rational r3 = new Rational(8,-7);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}

	@Test
	public void testDiv3(){ // Divide 1 pos, 1 neg
		Rational r1 = new Rational(2, 7);
		Rational r2 = new Rational(1, -4);
		r1.divide(r2);
		Rational r3 = new Rational(8,-7);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}

	
	@Test (expected = IllegalArgumentException.class)
	public void testDiv0(){ // Divide w/ r2 having 0 in numerator 
		Rational r1 = new Rational(2, 7);
		Rational r2 = new Rational(0, 4);
		
		r1.divide(r2);
		
		Rational r3 = new Rational(8,7);
		Boolean eq = r1.equals(r3);
		
		assertTrue(eq == true);	
	}

	
	
}
