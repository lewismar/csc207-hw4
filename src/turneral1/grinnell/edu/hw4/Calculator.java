/*
 * @Author Alex Turner, John Brady, and Mark Lewis
 */
package turneral1.grinnell.edu.hw4;

import java.math.BigInteger;

public class Calculator {
    // initialize storage elements
    private int storageSize;
    private double r[] = new double[storageSize];
    
    public Calculator() throws Exception {
	// minimum storage size
	this.storageSize = 8;
	this.r = new double[this.storageSize];
    } // Calculator()
    
    public Calculator(int size) throws Exception {
	this.storageSize = size;
	this.r = new double[this.storageSize];
    } // Calculator(int storageSize)

    // Much of the code is taken from the Calculator class for Alex Turner and
    //  Daniel Goldstein's csc-207/hw3 repository
    public double evaluate(String expression) throws Exception {
	int storageIndex = -1;
	double value = 0;
	
	if (expression.length() >= 5) {
	    if ((expression.substring(0,5)).matches("[r]\\d = ")) {
		// store the specified index
		storageIndex = expression.charAt(1) - 48;
		// truncate the assignment from the expression
		expression = expression.substring(expression.indexOf(' ',expression.indexOf(' ')+1)+1);
	    } // if
	} // if
	
	char symbol = '+';
	for (int j = 0; j<expression.indexOf(' '); j++) {
	    value += (expression.charAt(j)-48)*Math.pow(10,expression.indexOf(' ')-j-1);
	} // for
	
	// store the next symbol
	symbol = expression.charAt(expression.indexOf(' ')+1);
	// moves to the next number
	expression = expression.substring(expression.indexOf(' ')+3);
	System.out.println(value + " " + expression);
	while(expression.length() > 0) {
	    double num = 0;
	    int len = 0;

	    if (expression.indexOf(' ') != -1) {
		len = expression.indexOf(' ');
	    } // if
	    else {
		len = expression.length();
	    } // else
	    
	    String expr = expression.substring(0,len);
	    
	    if (expr.matches("[r]\\d")) { // stored value
		// check that r[num] has a value
		num = r[expr.charAt(expr.indexOf('r')+1)-48];
	    } // if

	    else if (expr.matches("\\d/\\d")) { // fraction
		int j = 0;
		BigInteger numerator = BigInteger.valueOf(0);
		
		for (; expression.charAt(j) != '/'; j++) {
		    numerator = numerator.add(BigInteger.valueOf((expression.charAt(j)-48)*((long)Math.pow(10,len-j-1))));
		} // for
		
		BigInteger denominator = BigInteger.valueOf(0);
		for (j++; j<len; j++) {
		    denominator = denominator.add(BigInteger.valueOf((expression.charAt(j)-48)*((long)Math.pow(10,len-j-1))));
		} // for
		
		num = (new Fraction(numerator,denominator)).decimalValue();
	    } // else if

	    else { // for normal integers
		for (int j = 0; j<len; j++) {
		    num += (expression.charAt(j)-48)*Math.pow(10,len-j-1);
		} // for
	    } // else

	    switch (symbol) {
	    case '+':
		value += num;
		break;
	    case '-':
		value -= num;
		break;
	    case '*':
		value *= num;
		break;
	    case '/':
		if (num != 0) {
		    value /= num;
		} // if
		break;
	    case '^':
		value = Math.pow(value,num);
		break;
	    } // switch

	    if (expression.indexOf(' ') == -1)
		break;

	    // store the next symbol
	    symbol = expression.charAt(expr.indexOf(' ')+1);
	    // moves to the next number
	    expression = expression.substring(expr.indexOf(' ')+3);
	} // while

	if (storageIndex > -1 && storageIndex < storageSize) {
	    // stores value
	    r[storageIndex] = value;
	} // if

	return value;
    } // evaluate(String expression)

    public double[] evaluate(String[] expressions) throws Exception {
	double values[] = new double[expressions.length];

	for(int i=0; i<expressions.length;i++) {
	    values[i] = evaluate(expressions[i]);
	} // for

	return values;
    } // evaluate(String[] expressions)
} // Calculator