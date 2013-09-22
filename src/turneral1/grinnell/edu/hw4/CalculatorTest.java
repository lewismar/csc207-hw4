package turneral1.grinnell.edu.hw4;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

    @Test
    public void test() throws Exception {
	Calculator ti84 = new Calculator(8);
	
	assertEquals("Positive addition", 7, ti84.evaluate("2 + 5"), .01);
	assertEquals("Positive subtraction", 3, ti84.evaluate("5 - 2"), .01);
	assertEquals("Positive multiplication", 10, ti84.evaluate("2 * 5"), .01);
	assertEquals("Positive division", 2, ti84.evaluate("10 / 5"), .01);
	
	assertEquals("Fractional input", 1, ti84.evaluate("1/1"), .01);
	
	assertEquals("Storage", 500, ti84.evaluate("r0 = 5 * 100"), .01);
	assertEquals("Retrieval", 500, ti84.evaluate("r0"), .01);
	assertEquals("Overwriting", 200, ti84.evaluate("r0 = 2 * 100"), .01);
	
    } // test()

} // CalculatorTest
