/*
 * @Author Alex Turner, John Brady, and Mark Lewis
 */
package turneral1.grinnell.edu.hw4;

import java.math.BigInteger;

/*
 * Class Calculator
 * 
 * Fields:
 *  int storageSize the number of members in r[].
 *  double r[storageSize] acts as memory for the calculator. 
 */
public class Calculator {
    private int storageSize;
    private double r[] = new double[storageSize];

    // Constructors
    
    public Calculator() {
	// minimum storage size is 8
	this.storageSize = 8;
	this.r = new double[this.storageSize];
    } // Calculator()

    public Calculator(int size) throws Exception {
	this.storageSize = 8;
    	if (size >= 8) {
            this.storageSize = size;
    	} // if
        this.r = new double[this.storageSize];
    } // Calculator(int)
    
    // Public methods
    
    /*
     * evaluate(String)
     * 
     * Preconditions:
     *  expression only contains the characters 
     *   0123456789r+-/*= ^
     * 
     * Postconditions:
     *  Evaluates the expression using naive left to right order of operations,
     *   returning the result as a double.
     *  If "r\d = (expression)", assigns the result of (expression) to r[\d].
     */
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
		try {
		    throw new Exception("Invalid input character.");
		} catch (Exception e) {
		    System.out.println("Invalid input character " + expression.charAt(i) + " at index " + i);
		} // catch
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
		// sets num to the value at r[number]
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
		value /= num;
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

    /*
     * evaluate(String[])
     * 
     * Preconditions:
     *  All Strings only contain the characters 
     *   0123456789r+-/*= ^
     * 
     * Postconditions:
     *  Returns each String's result in an array. See postconditions for
     *   evaluate(String).
     */
    public double[] evaluate(String[] expressions) throws Exception {
	double values[] = new double[expressions.length];

	for(int i=0; i<expressions.length; i++) {
	    values[i] = evaluate(expressions[i]);
	} // for

	return values;
    } // evaluate(String[] expressions)
} // Calculator