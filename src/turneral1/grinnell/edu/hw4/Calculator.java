/*
 * @Author Alex Turner, John Brady, and Mark Lewis
 */
package turneral1.grinnell.edu.hw4;

import java.math.BigInteger;

public class Calculator {
    // initialize storage elements
    private int storageSize;
    private double r[] = new double[storageSize];

    public Calculator() {
	// minimum storage size is 8
	this.storageSize = 8;
	this.r = new double[this.storageSize];
    } // Calculator()

    public Calculator(int size) throws Exception {
	this.storageSize = size;
	this.r = new double[this.storageSize];
    } // Calculator(int)

    // Much of the code is taken from the Calculator class for Alex Turner and
    //  Daniel Goldstein's csc-207/hw3 repository
    public double evaluate(String expression) throws Exception {
	/* 
	 * storageIndex == -1 denotes no assignment. 
	 * Otherwise, r[storageIndex] = value.
	 */
	int storageIndex = -1, i;
	// the return value
	double value = 0;
	char symbol = '+';

	/*
	 * Check to see if the expression is malformed.
	 * Accepted characters:
	 *  [0123456789r+-/*= ^]
	 */
	for (i = 0; i < expression.length(); i++){
	    if ((expression.substring(i,i+1)).matches("[^\\dr\\+\\-/\\*= \\^]")) {
		throw new Exception("Invalid input character " + expression.charAt(i) + " at index " + i);
	    } // if
	} // for

	// expression begins with an assignment call check
	if ((expression.substring(0,2).matches("[r]\\d"))) {
	    if (expression.length() >= 5 && (expression.substring(0,5)).matches("[r]\\d = ")) {
		// store the specified index
		storageIndex = expression.charAt(1) - 48;
		// truncate the assignment from the expression
		expression = expression.substring(expression.indexOf(' ',expression.indexOf(' ')+1)+1);
	    } // if
	    else  {
		value = r[expression.charAt(1) - 48];
		// store the next symbol
		symbol = expression.charAt(expression.indexOf(' ')+1);
		// moves to the next number
		expression = expression.substring(expression.indexOf(' ')+3);
	    } // else
	} // if

	//read expression
	while(expression.length() > 0) {
	    boolean isNegative = false;
	    double num = 0;
	    int len = 0;

	    // the number is negative
	    if(expression.charAt(0) == '-') {
		isNegative = !isNegative;
		expression = expression.substring(1);
	    } // else
	    if (expression.indexOf(' ') != -1) {
		len = expression.indexOf(' ');
	    } // if
	    else {
		len = expression.length();
	    } // else

	    String expr = expression.substring(0,len);

	    if (expr.matches("[r]\\d")) { // stored value
		// check that r[d] has a value
		num = r[expr.charAt(expr.indexOf('r')+1)-48];
	    } // if

	    // fractional input
	    else if (expr.matches("\\d/\\d") || expr.matches("\\d/\\-\\d")) { 
		i = 0;
		BigInteger numerator = BigInteger.valueOf(0);

		// read numerator
		for (; expression.charAt(i) != '/'; i++) {
		    numerator = numerator.add(BigInteger.valueOf((expression.charAt(i)-48)*((long)Math.pow(10,expression.indexOf('/')-i-1))));
		} // for		

		// the denominator is negative
		if(expression.charAt(i+1) == '-') {
		    isNegative = !isNegative;
		    i++;
		} // if

		// read denominator
		BigInteger denominator = BigInteger.valueOf(0);
		for (i++; i<len; i++) {
		    denominator = denominator.add(BigInteger.valueOf((expression.charAt(i)-48)*((long)Math.pow(10,len-i-1))));
		} // for

		num = (new Fraction(numerator,denominator)).decimalValue();
	    } // else if

	    // for normal integers
	    else { 
		for (i = 0; i<len; i++) {
		    num += (expression.charAt(i)-48)*Math.pow(10,len-i-1);
		} // for
	    } // else

	    if (isNegative) {
		num *= -1;
	    } // if

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
		    // ERROR
		} // if
		break;
	    case '^':
		value = Math.pow(value,num);
		break;
	    } // switch

	    if (expression.indexOf(' ') == -1) {
		break;
	    } // if

	    // store the next symbol
	    symbol = expression.charAt(expression.indexOf(' ')+1);
	    // moves to the next number
	    expression = expression.substring(expression.indexOf(' ')+3);
	} // while

	if (storageIndex > -1 && storageIndex < storageSize) {
	    // stores value
	    r[storageIndex] = value;
	} // if

	return value;
    } // evaluate(String expression)

    public double[] evaluate(String[] expressions) throws Exception {
	double values[] = new double[expressions.length];

	for(int i=0; i<expressions.length; i++) {
	    values[i] = evaluate(expressions[i]);
	} // for

	return values;
    } // evaluate(String[] expressions)
} // Calculator