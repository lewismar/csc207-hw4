
package turneral1.grinnell.edu.hw4;

import java.math.BigInteger;

/* @Mark Lewis
 * @Alex Turner
 * @John Brady
 * 
 * CSC207-hw4
 * Rebelsky
 * 9/24/13
 */

// Much of this code was taken from hw2 
// (completed by Tiffany Nguyen, Daniel Goldstein, Mark Lewis, and Earnest Wheeler)
public class Fraction {
    private BigInteger numerator;
    private BigInteger denominator;

    /* --------------
     * |Constructors|
     * --------------
     */
    public Fraction(int n, int d) throws Exception {
	if(d == 0) {
	    throw new Exception("Cannot divide by zero.");
	} // if
	this.numerator = BigInteger.valueOf(n);
	this.denominator = BigInteger.valueOf(d);
	this.cleanup();
    } // Fraction
    public Fraction(BigInteger n, BigInteger d) throws Exception {
	if(d.compareTo(BigInteger.valueOf(0)) == 0) {
	    throw new Exception("Cannot divide by zero.");
	} // if
	this.numerator = n;
	this.denominator = d;
	this.cleanup();
    } // Fraction
    public Fraction(double n) throws Exception{
	this.numerator = BigInteger.valueOf((long) (n*(2^18)));
	this.denominator = BigInteger.valueOf(2^18);
	this.cleanup();
    } //Fraction
    public Fraction(long num, long den) throws Exception{
	this.numerator = BigInteger.valueOf(num);
	this.denominator = BigInteger.valueOf(den);
	this.cleanup();
    } //Fraction
    public Fraction(int num) throws Exception{
	this.numerator = BigInteger.valueOf(num);
	this.denominator = BigInteger.valueOf(1);
	this.cleanup();
    } //Fraction
    public Fraction(long num) throws Exception{
	this.numerator = BigInteger.valueOf(num);
	this.denominator = BigInteger.valueOf(1);
	this.cleanup();
    } //Fraction
    public Fraction(BigInteger num) throws Exception{
	this.numerator = num;
	this.denominator = BigInteger.valueOf(1);
	this.cleanup();
    } //Fraction
    /*
     * str must be in the form xxx/xxx.
     * There cannot be space.
     * Only accepts integer values.
     */
    public Fraction(String str) throws Exception{
	BigInteger num;
	BigInteger den;
	String numStr = "";
	String denStr = "";
	int i = 0;
	if(str.length() == 0) {
	    throw new Exception ("no numbers provided");
	} //if
	while(str.charAt(i) != '/' && i < str.length()) {
	    numStr += str.charAt(i);
	    i++;
	} //while

	denStr += str.substring(i, str.length());
	if (denStr.length() == 0) {
	    den = BigInteger.valueOf(1);
	} //if
	else {
	    den = BigInteger.valueOf(Integer.parseInt(denStr));
	} //else

	num = BigInteger.valueOf(Integer.parseInt(numStr));
	if(den.compareTo(BigInteger.valueOf(0)) == 0) {
	    throw new Exception("Cannot divide by zero.");
	} // if

	this.numerator = num;
	this.denominator = den;
	this.cleanup();
    } //Fraction
    /* ----------------
     * |Public Methods|
     * ----------------
     */
    public int compareTo (Fraction other) {
	if (this.equals(other)) {
	    return 0;
	}
	else if (this.decimalValue() > other.decimalValue()) { //other is smaller than this
	    return 1;
	}
	return -1;
	    
    } //compareTo
    
    public static Fraction negate (Fraction input) throws Exception{
	Fraction output = new Fraction((input.getNumerator()).multiply(BigInteger.valueOf(-1)), input.getDenominator());
	output.cleanup();
	return output;	
    } //negate

    public static Fraction reciprocal (Fraction input) throws Exception {
	Fraction output = new Fraction(input.getDenominator(),input.getNumerator());
	output.cleanup();
	return output;
    } //reciprocal
    
    public static Fraction fractionalPart (Fraction input) throws Exception {
	Fraction output = new Fraction(input.getNumerator().mod(input.getDenominator()),input.getDenominator());
	output.cleanup();
	return output;	
    } //fractionalPart
    
    public static BigInteger wholePart (Fraction input) throws Exception {
	return(input.getNumerator().divide(input.getDenominator()));
    } //wholePart
    
    public BigInteger getNumerator() {
	return this.numerator;
    } // getNumerator

    /*
     * Observer, retrieves the private denominator value of the Fraction object
     */
    public BigInteger getDenominator() {
	return this.denominator;
    } // getDenominator

    /*
     * Mutator, to be able to change the value of the Fraction number
     */
    public static Fraction multiply(Fraction multiplier, Fraction multiplier2) throws Exception {
	BigInteger num = multiplier.getNumerator().multiply(multiplier2.getNumerator());
	BigInteger den = multiplier.getDenominator().multiply(multiplier2.getDenominator());
	Fraction multiplicand = new Fraction (num, den);
	multiplicand.cleanup();
	return multiplicand;
    } // multiply

    /*
     * Mutator, to be able to change the value of the Fraction number
     */
    public static Fraction divide(Fraction dividend, Fraction divisor) throws Exception {
	Fraction recip = new Fraction(divisor.getDenominator(), divisor.getNumerator());
	return multiply(dividend, recip);
    } // divide

    /*
     * Observer, does not change any values stored but returns
     * the numerator/denominator. 
     * Note: Precision is lost in this method
     */
    public double decimalValue(){
	return (this.numerator.doubleValue() / this.denominator.doubleValue());
    } // computeValue

    /*
     * Mutator, adds a Fraction number to a Fraction number
     */
    public static Fraction add(Fraction toAdd, Fraction twoAdd) throws Exception {
	BigInteger num = (toAdd.getNumerator().multiply(twoAdd.getDenominator())).add(twoAdd.getNumerator().multiply(toAdd.getDenominator()));
	BigInteger den = toAdd.getDenominator().multiply(twoAdd.getDenominator());
	Fraction result = new Fraction(num, den);
	result.cleanup();
	return result;
    } // add

    /*
     * Mutator, subtracts a Fraction number to a Fraction number
     */
    public static Fraction subtract(Fraction toSubtract, Fraction twoSubtract) throws Exception {
	Fraction neg = new Fraction(twoSubtract.getNumerator().multiply(BigInteger.valueOf(-1)), twoSubtract.getDenominator());
	return add(toSubtract, neg);
    } // subtract


    /*
     * Returns the string representation of the fraction.
     */
    public String toString() {
	return (this.numerator + "/" + this.denominator);
    } // toString
    public int hashCode() { 
	return numerator.hashCode() * denominator.hashCode(); 
    } //hasCode

    public Fraction clones() throws Exception{
	Fraction cloned = new Fraction (this.getNumerator(),this.getDenominator());
	cloned.cleanup();
	return cloned;
    } //clone


    public boolean equals(Object obj) {
	return (this.hashCode() == obj.hashCode());
    } //equals 
    /* ----------------
     * |Private Methods|
     * ----------------
     */

    /*
     * simplify reduces a Fraction to the simplest terms.
     */
    private void simplify() throws Exception {
	BigInteger gcd = this.denominator.gcd(this.numerator);
	this.denominator.divide(gcd);
	this.numerator.divide(gcd);
    } // simplify


    /*
     * If the fraction is negative, this function ensures that the negative number is within the numerator
     * It also changes -numerator/-denominator to +numerator/+denominator
     */
    private void fixNegs() {
	if(this.denominator.compareTo(BigInteger.valueOf(0)) == -1) {        
	    this.numerator.multiply(BigInteger.valueOf(-1));
	    this.denominator.multiply(BigInteger.valueOf(-1));
	} //if
    } // fixNegs

    /*
     * cleans up the fraction class by simplifying and fixing negative numbers
     */
    private void cleanup() throws Exception{
	this.simplify();
	this.fixNegs();
    } //cleanup 

} // Fraction
