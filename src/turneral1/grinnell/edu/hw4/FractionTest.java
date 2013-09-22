package turneral1.grinnell.edu.hw4;

import static org.junit.Assert.*;

import org.junit.Test;

public class FractionTest {

    Fraction exampleOne = new Fraction (1, 1);
    Fraction exampleTwo = new Fraction (7, 23);
    Fraction exampleThree = new Fraction (3, 2);
    Fraction exampleFour = new Fraction (-1, 4);

    @Test
    public void testGetNumerator() {
    assertEquals("example one numerator", 1, exampleOne.getNumerator());
    assertEquals("example two numerator", 7, exampleTwo.getNumerator());
    assertEquals("example three numerator", 3, exampleThree.getNumerator());
    assertEquals("example four numerator", -1, exampleFour.getNumerator());
    } // testGetNumerator

    @Test
    public void testGetDenominator() {
    assertEquals("example one denominator", 1, exampleOne.getDenominator());
    assertEquals("example two denominator", 23, exampleTwo.getDenominator());
    assertEquals("example three denominator", 2, exampleThree.getDenominator());
    assertEquals("example four denominator", 4, exampleFour.getDenominator());
    } // testGetDenominator

    @Test
    public void testMultiply() throws Exception {
    Fraction exampleOneMultiplied = new Fraction(exampleOne.getNumerator(), exampleOne.getDenominator());
    exampleOneMultiplied.multiply(exampleOneMultiplied, exampleFour);
    Fraction exampleTwoMultiplied = new Fraction(exampleTwo.getNumerator(), exampleTwo.getDenominator());
    exampleTwoMultiplied.multiply(exampleTwoMultiplied, exampleThree);
    assertEquals("example one multiply numerator", -1, exampleOneMultiplied.getNumerator());
    assertEquals("example one multiply denominator", 4, exampleOneMultiplied.getDenominator());
    assertEquals("example two multiply numerator", 21, exampleTwoMultiplied.getNumerator());
    assertEquals("example two multiply denominator", 46, exampleTwoMultiplied.getDenominator());
    } // testMultiply

    @Test
    public void testDivide() throws Exception {
    Fraction exampleOneDivided = new Fraction(exampleOne.getNumerator(), exampleOne.getDenominator());
    exampleOneDivided.divide(exampleOneDivided, exampleFour);
    Fraction exampleTwoDivided = new Fraction(exampleTwo.getNumerator(), exampleTwo.getDenominator());
    Fraction.divide(exampleTwoDivided, exampleThree);
    assertEquals("example one divide numerator", 4, exampleOneDivided.getNumerator());
    assertEquals("example one divide denominator", -1, exampleOneDivided.getDenominator());
    assertEquals("example two divide numerator", 14, exampleTwoDivided.getNumerator());
    assertEquals("example two divide denominator", 69, exampleTwoDivided.getDenominator());
    } // testDivide

    @Test
    public void testComputeValue() {    
    assertEquals("exampleOne value", 1.0/1.0, exampleOne.decimalValue(), .1);
    assertEquals("exampleTwo value", 7.0/23.0, exampleTwo.decimalValue(), .1);
    assertEquals("exampleThree value", 3.0/2.0, exampleThree.decimalValue(), .1);    
    assertEquals("exampleFour value", -1.0/4.0, exampleFour.decimalValue(), .1);
    } // testComputeValue

    @Test
    public void testAdd() throws Exception {
    Fraction exampleOneAdded = new Fraction(exampleOne.getNumerator(), exampleOne.getDenominator());
    exampleOneAdded.add(exampleOneAdded, exampleFour);
    Fraction exampleTwoAdded = new Fraction(exampleTwo.getNumerator(), exampleTwo.getDenominator());
    exampleTwoAdded.add(exampleTwoAdded, exampleThree);
    assertEquals("example one add numerator", 3, exampleOneAdded.getNumerator());
    assertEquals("example one add denominator", 4, exampleOneAdded.getDenominator());
    assertEquals("example two add numerator", 83, exampleTwoAdded.getNumerator());
    assertEquals("example two add denominator", 46, exampleTwoAdded.getDenominator());
    } // testAdd

    @Test
    public void testSubtract() throws Exception {
    Fraction exampleOneSubtracted = new Fraction(exampleOne.getNumerator(), exampleOne.getDenominator());
    exampleOneSubtracted.subtract(exampleOneSubtracted, exampleFour);
    Fraction exampleTwoSubtracted = new Fraction(exampleTwo.getNumerator(), exampleTwo.getDenominator());
    exampleTwoSubtracted.subtract(exampleTwoSubtracted, exampleThree);
    assertEquals("example one subtract numerator", 5, exampleOneSubtracted.getNumerator());
    assertEquals("example one subtract denominator", 4, exampleOneSubtracted.getDenominator());
    assertEquals("example two subtract numerator", -55, exampleTwoSubtracted.getNumerator());
    assertEquals("example two subtract denominator", 46, exampleTwoSubtracted.getDenominator());
    } // testSubtract
    
    @Test
    public void testSimplify() throws Exception{
	Fraction exampleOne = new Fraction(1,1);
	exampleOne.simplify();
    assertEquals("1/1 simplified", new Fraction(1,1), exampleOne);
    } // testSimplify
} // FractionTest