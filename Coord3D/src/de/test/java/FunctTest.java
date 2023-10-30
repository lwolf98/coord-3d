package de.test.java;

import de.coord.java.interpreter.FunctionElements;

public class FunctTest {
	public static void main(String[] args) {
		method1();
	}
	
	public static void method1() {
		String term = "3.25*4+2.5+x";
		double x = 0.5;
		
		FunctionElements function = new FunctionElements(term);
		function.initialize();
		if(function.elementsAreAssigned())
			System.out.println(term + " für x = " + x + " = " + function.solve(x).get());
		else
			System.out.println("not assigned");
	}
}
