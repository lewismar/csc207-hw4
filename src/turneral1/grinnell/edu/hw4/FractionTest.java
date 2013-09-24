package turneral1.grinnell.edu.hw4;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class FractionTest {

    Fraction exampleOne;
    Fraction exampleTwo;
    Fraction exampleThree;
    Fraction exampleFour;

    public void initExamples() throws Exception {
	exampleOne = new Fraction(1, 1);
	exampleTwo = new Fraction(7, 23);
	exampleThree = new Fraction(3, 2);
	exampleFour = new Fraction(-1, 4);
    } // initExamples

    @Test
    public void testGetNumerator() throws Exception {
	initExamples();
	assertEquals("example one numerator", BigInteger.valueOf(1),
		exampleOne.getNumerator());
	assertEquals("example two numerator", BigInteger.valueOf(7),
		exampleTwo.getNumerator());
	assertEquals("example three numerator", BigInteger.valueOf(3),
		exampleThree.getNumerator());
	assertEquals("example four numerator", BigInteger.valueOf(-1),
		exampleFour.getNumerator());
    } // testGetNumerator

    @Test
    public void testGetDenominator() throws Exception {
	initExamples();
	assertEquals("example one denominator", BigInteger.valueOf(1),
		exampleOne.getDenominator());
	assertEquals("example two denominator", BigInteger.valueOf(23),
		exampleTwo.getDenominator());
	assertEquals("example three denominator", BigInteger.valueOf(2),
		exampleThree.getDenominator());
	assertEquals("example four denominator", BigInteger.valueOf(4),
		exampleFour.getDenominator());
    } // testGetDenominator

    @Test
    public void testMultiply() throws Exception {
	initExamples();
	Fraction exampleOneMult;
	exampleOneMult = Fraction.multiply(exampleFour, exampleThree);
	assertEquals("example one multiply numerator", BigInteger.valueOf(-3),
		exampleOneMult.getNumerator());
	assertEquals("example one multiply denominator", BigInteger.valueOf(8),
		exampleOneMult.getDenominator());
    } // testMultiply

    @Test
    public void testDivide() throws Exception {
	initExamples();
	Fraction exampleOneDivide;
	exampleOneDivide = Fraction.divide(exampleThree, exampleOne);
	assertEquals("example one divide numerator", BigInteger.valueOf(3),
		exampleOneDivide.getNumerator());
	assertEquals("example one divide denominator", BigInteger.valueOf(2),
		exampleOneDivide.getDenominator());

    } // testDivide

    @Test
    public void testComputeValue() throws Exception {
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
	Fraction exampleOneAdded;
	exampleOneAdded = Fraction.add(exampleOne, exampleThree);
	assertEquals("example one add numerator", BigInteger.valueOf(5),
		exampleOneAdded.getNumerator());
	assertEquals("example one add denominator", BigInteger.valueOf(2),
		exampleOneAdded.getDenominator());
    } // testAdd

    @Test
    public void testSubtract() throws Exception {
	initExamples();
	Fraction exampleOneSub;
	exampleOneSub = Fraction.subtract(exampleThree, exampleOne);
	assertEquals("example one subtract numerator", BigInteger.valueOf(1),
		exampleOneSub.getNumerator());
	assertEquals("example one subtract denominator", BigInteger.valueOf(2),
		exampleOneSub.getDenominator());
    } // testSubtract

    @Test
    public void testToString() throws Exception {
	initExamples();
	assertEquals("example one toString", "1/1", exampleOne.toString());
    }

    @Test
    public void testWholePart() throws Exception {
	initExamples();
	assertEquals("example three", BigInteger.valueOf(1),
		exampleThree.wholePart());
    }

    @Test
    public void testFractionPart() throws Exception {
	initExamples();
	assertEquals("example one FractionPart", BigInteger.valueOf(1),
		exampleThree.fractionalPart().getNumerator());
	assertEquals("example one FractionPart", BigInteger.valueOf(2),
		exampleThree.fractionalPart().getDenominator());
    }

    @Test
    public void testReciprocal() throws Exception {
	initExamples();
	assertEquals("example one reciprocal", BigInteger.valueOf(2),
		exampleThree.reciprocal().getNumerator());
	assertEquals("example one reciprocal", BigInteger.valueOf(3),
		exampleThree.reciprocal().getDenominator());
    }

    @Test
    public void testNegate() throws Exception {
	initExamples();
	assertEquals("example one negate", BigInteger.valueOf(-3), exampleThree
		.negate().getNumerator());
	assertEquals("example one negate", BigInteger.valueOf(2), exampleThree
		.negate().getDenominator());
    }

    @Test
    // this tests the equals, hashcode, and Fraction(string) methods
    public void testEquals() throws Exception {
	initExamples();
	Fraction fromString = new Fraction("3/2");
	assertEquals("example one equals", true,
		exampleThree.equals(fromString));
	assertEquals("example two equals", false,
		exampleFour.equals(fromString));
    }

    @Test
    public void testCompareTo() throws Exception {
	initExamples();
	assertEquals("example one equals", 0, exampleOne.compareTo(exampleOne));
	assertEquals("example one equals", -1,
		exampleOne.compareTo(exampleThree));
	assertEquals("example one equals", 1, exampleOne.compareTo(exampleFour));

    }

} // FractionTest