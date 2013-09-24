package turneral1.grinnell.edu.hw4;

	/*
	 * A friendly UI for the calculator class that supports fractions
	 */
public class CalculatorUI {
    
    public static void main(String[] args) throws Exception {
	Calculator calc = new Calculator();
	java.io.PrintWriter pen;
	pen = new java.io.PrintWriter(System.out, true);
	java.io.BufferedReader eyes;
	java.io.InputStreamReader istream;
	istream = new java.io.InputStreamReader(System.in);
	eyes = new java.io.BufferedReader(istream);
	String input = "not q";

	while (true) {
	    pen.println("Enter an expression to be evaluated (spaces between each variable and operator). Enter Q to quit: ");

	    input = eyes.readLine();
	    if(input.equals("Q")) {
		pen.println("Goodbye D*:" );
		break;
	    }
	    pen.println(calc.evaluate(input));
	    
	}// while
	 // cleanup
	pen.close();
	eyes.close();
    } // main

} // CalculatorUI
