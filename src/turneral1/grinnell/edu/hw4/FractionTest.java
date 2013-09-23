package turneral1.grinnell.edu.hw4;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class FractionTest {

    Fraction exampleOne;
    Fraction exampleTwo;
    Fraction exampleThree; 
    Fraction exampleFour;

    public void initExamples() throws Exception{
	exampleOne = new Fraction(1, 1);
	exampleTwo = new Fraction(7, 23);
	exampleThree = new Fraction(3, 2);
	exampleFour = new Fraction(-1, 4);
    } // initExamples

    @Test
    public void testGetNumerator() throws Exception{
	initExamples();
	assertEquals("example one numerator", BigInteger.valueOf(1), exampleOne.getNumerator());
	assertEquals("example two numerator", BigInteger.valueOf(7), exampleTwo.getNumerator());
	assertEquals("example three numerator", BigInteger.valueOf(3), exampleThree.getNumerator());
	assertEquals("example four numerator", BigInteger.valueOf(-1), exampleFour.getNumerator());
    } // testGetNumerator

    @Test
    public void testGetDenominator() throws Exception{
	initExamples();
	assertEquals("example one denominator", BigInteger.valueOf(1), exampleOne.getDenominator());
	assertEquals("example two denominator", BigInteger.valueOf(23), exampleTwo.getDenominator());
	assertEquals("example three denominator", BigInteger.valueOf(2),
		exampleThree.getDenominator());
	assertEquals("example four denominator", BigInteger.valueOf(4),
		exampleFour.getDenominator());
    } // testGetDenominator

    @Test
    public void testMultiply() throws Exception {
	initExamples();
	Fraction exampleOneMultiplied = new Fraction(exampleOne.getNumerator(),
		exampleOne.getDenominator());
	exampleOneMultiplied.multiply(exampleOneMultiplied, exampleFour);
	Fraction exampleTwoMultiplied = new Fraction(exampleTwo.getNumerator(),
		exampleTwo.getDenominator());
	exampleTwoMultiplied.multiply(exampleTwoMultiplied, exampleThree);
	assertEquals("example one multiply numerator", BigInteger.valueOf(-1),
		exampleOneMultiplied.getNumerator());
	assertEquals("example one multiply denominator", BigInteger.valueOf(4),
		exampleOneMultiplied.getDenominator());
	assertEquals("example two multiply numerator", BigInteger.valueOf(21),
		exampleTwoMultiplied.getNumerator());
	assertEquals("example two multiply denominator", BigInteger.valueOf(46),
		exampleTwoMultiplied.getDenominator());
    } // testMultiply

    @Test
    public void testDivide() throws Exception {
	initExamples();
	Fraction exampleOneDivided = new Fraction(exampleOne.getNumerator(),
		exampleOne.getDenominator());
	exampleOneDivided.divide(exampleOneDivided, exampleFour);
	Fraction exampleTwoDivided = new Fraction(exampleTwo.getNumerator(),
		exampleTwo.getDenominator());
	Fraction.divide(exampleTwoDivided, exampleThree);
	assertEquals("example one divide numerator", BigInteger.valueOf(4),
		exampleOneDivided.getNumerator());
	assertEquals("example one divide denominator", BigInteger.valueOf(-1),
		exampleOneDivided.getDenominator());
	assertEquals("example two divide numerator", BigInteger.valueOf(14),
		exampleTwoDivided.getNumerator());
	assertEquals("example two divide denominator", BigInteger.valueOf(69),
		exampleTwoDivided.getDenominator());
    } // testDivide

    @Test
    public void testComputeValue() throws Exception{
	initExamples();
	assertEquals("exampleOne value", 1.0 / 1.0, exampleOne.decimalValue(),
		.1);
	assertEquals("exampleTwo value", 7.0 / 23.0, exampleTwo.decimalValue(),
		.1);
	assertEquals("exampleThree value", 3.0 / 2.0,
		exampleThree.decimalValue(), .1);
	assertEquals("exampleFour value", -1.0 / 4.0,
		exampleFour.decimalValue(), .1);
    } // testComputeValue

    @Test
    public void testAdd() throws Exception {
	initExamples();
	Fraction exampleOneAdded = new Fraction(exampleOne.getNumerator(),
		exampleOne.getDenominator());
	exampleOneAdded.add(exampleOneAdded, exampleFour);
	Fraction exampleTwoAdded = new Fraction(exampleTwo.getNumerator(),
		exampleTwo.getDenominator());
	exampleTwoAdded.add(exampleTwoAdded, exampleThree);
	assertEquals("example one add numerator", BigInteger.valueOf(1), 
		exampleOneAdded.getNumerator());
	assertEquals("example one add denominator", BigInteger.valueOf(4),
		exampleOneAdded.getDenominator());
	assertEquals("example two add numerator", BigInteger.valueOf(83),
		exampleTwoAdded.getNumerator());
	assertEquals("example two add denominator", BigInteger.valueOf(46),
		exampleTwoAdded.getDenominator());
    } // testAdd

    @Test
    public void testSubtract() throws Exception {
	initExamples();
	Fraction exampleOneSubtracted = new Fraction(exampleOne.getNumerator(),
		exampleOne.getDenominator());
	exampleOneSubtracted.subtract(exampleOneSubtracted, exampleFour);
	Fraction exampleTwoSubtracted = new Fraction(exampleTwo.getNumerator(),
		exampleTwo.getDenominator());
	exampleTwoSubtracted.subtract(exampleTwoSubtracted, exampleThree);
	assertEquals("example one subtract numerator", BigInteger.valueOf(5),
		exampleOneSubtracted.getNumerator());
	assertEquals("example one subtract denominator", BigInteger.valueOf(4),
		exampleOneSubtracted.getDenominator());
	assertEquals("example two subtract numerator", BigInteger.valueOf(-55),
		exampleTwoSubtracted.getNumerator());
	assertEquals("example two subtract denominator", BigInteger.valueOf(46),
		exampleTwoSubtracted.getDenominator());
    } // testSubtract

   /*@Test
    //we can't test this, as it is a private function. Change to public for testing, then revert? 
    public void testSimplify() throws Exception {
	initExamples();
	Fraction exampleOne = new Fraction(1, 1);
	exampleOne.simplify();
	assertEquals("1/1 simplified", new Fraction(1, 1), exampleOne);
    } // testSimplify
    */
} // FractionTest