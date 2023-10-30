package de.coord.test;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TestExp4j {
	public static void main(String[] args) {
		String expr = "3x^2";
		double x = 3.3;
		Expression e = new ExpressionBuilder(expr)
				.variables("x")
				.build()
				.setVariable("x", x);
		
		System.out.println("Result of " + expr + " for x = " + x + " is: " + e.evaluate());
	}
}
