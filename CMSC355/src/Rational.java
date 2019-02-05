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
public class Rational {

	private int num;
	private int den;
	
	/**
	 * Default Constructor
	*/
	public Rational() {
		setNum(1);
		setDen(1);
	}
	
	/**
	 * Overloaded constructor to create user's fraction
	 * @param num - numerator
	 * @param den - denominator
	 */
	public Rational(int num, int den) throws IllegalArgumentException{
		setNum(num);
		setDen(den);
		normalize();
	}
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * Setter for numerator
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
	/**
	 * @return the den
	 */
	public int getDen() {
		return den;
	}

	/**
	 * Setter for denominator
	 * @param den - What to set the denominator to.
	 * @throws IllegalArgumentException
	 */
	public void setDen(int den) throws IllegalArgumentException {
		if (den == 0){
			throw new IllegalArgumentException("Cannot set denominator to 0");
		} else {
			this.den = den;
			normalize();
		}
			
	}
	
	/**
	 * Rational numbers must have only 1 negative symbol if negative.  If it is
	 * negative, the symbol must be on the numerator and never the denominator.
	 * @param r - Rational object to normalize
	 */
	public void normalize(){
		if (this.num < 0 && this.den < 0){
			this.setNum(this.num*-1);
			this.setDen(this.den*-1);
		} else if (this.num > 0 && this.den < 0){
			this.setNum(this.num*-1);
			this.setDen(this.den*-1);
		} 
	}
	
	/**
	 * Add two rational numbers
	 * @param r - rational number to add
	 */
	public void add(Rational r){
		this.setNum(this.num*r.getDen() + r.getNum()*this.den);
		this.setDen(this.den * r.getDen());
		normalize();
	}
	
	/**
	 * Subtract two rational numbers
	 * @param r - rational number to subtract
	 */
	public void subtract(Rational r){
		this.setNum(this.num*r.getDen() - r.getNum()*this.den);
		this.setDen(this.den * r.getDen());
	}
	
	/**
	 * Multiply two rational numbers
	 * @param r - rational number to multiply
	 */
	public void multiply(Rational r){
		this.setNum(this.num * r.getNum());
		this.setDen(this.den * r.getDen());
	}
		
	/**
	 * Divide two rational numbers
	 * @param r - rational number to divide
	 * @throws IllegalArgumentException - if r's numerator is 0
	 */
	public void divide(Rational r) throws IllegalArgumentException{
		if (r.getNum() != 0) {
			this.setNum(this.num*r.getDen());
			this.setDen(this.den*r.getNum()); 
			
		} else {
			throw new IllegalArgumentException("Cannot divide by 0");
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		Rational other; // = new Rational(this.num,this.den);
		
		if (!(obj instanceof Rational)) return false;
		else other = (Rational) obj;
		
		// Rational numbers a/b and c/d are eqaul if a*d = c*b
		if (this.num * other.getDen() == this.den*other.getNum()) {
			return true; 
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getNum() + "/" + getDen();
	}
		
}
