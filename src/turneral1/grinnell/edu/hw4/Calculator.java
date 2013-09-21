/*
 * @Author Alex Turner, John Brady, and Mark Lewis
 */
package turneral1.grinnell.edu.hw4;

import java.math.BigInteger;

public class Calculator {
    // initialize storage elements
    static private int storageSize = 8;
    static private double r[] = new double[storageSize];
    
    // Much of the code is taken from the Calculator class for Alex Turner and
    //  Daniel Goldstein's csc-207/hw3 repository
    static public double evaluate(String expression) throws Exception {
    int storageIndex = -1;
    double value = 0;
    
    if ((expression.substring(0,5)).matches("[r]\\d = ")) {
        // store the specified index
        storageIndex = expression.charAt(1) - 48;
        // truncate the assignment from the expression
        expression = expression.substring(expression.indexOf(2, ' ')+1);
    } // if

    char symbol = '+';

    while(expression.length() > 0) {
        double num = 0;
        int len = 0;

        if (expression.indexOf(' ') != -1) {
        len = expression.indexOf(' ');
        } // if
        else
        len = expression.length();
        String expr = expression.substring(0,len);
        
        if (expr.matches(" [r]\\d ")) { // stored value
        // check that r[num] has a value
        num = r[expr.charAt(expr.indexOf('r')+1)-48];
        } // if
        
        else if (expr.matches(" \\d/\\d ")) { // fraction
        int j = 0;
        BigInteger numerator = BigInteger.valueOf(0);
        for (; j<len && expression.charAt(j) != '/'; j++)
            numerator.add(BigInteger.valueOf((expression.charAt(j)-48)*((long)Math.pow(10,len-j-1))));
        BigInteger denominator = BigInteger.valueOf(0);
        for (; j<len && expression.charAt(j) != ' '; j++)
            numerator.add(BigInteger.valueOf((expression.charAt(j)-48)*((long)Math.pow(10,len-j-1))));
        num = (new Fraction(numerator,denominator)).decimalValue();
        } // else if
        
        else { // for normal integers
        for (int j = 0; j<len && expression.charAt(j) != ' '; j++)
            num += (expression.charAt(j)-48)*Math.pow(10,len-j-1);
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