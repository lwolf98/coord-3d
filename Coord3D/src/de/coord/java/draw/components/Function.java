package de.coord.java.draw.components;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Function extends FunctionGeneral {
	private Expression expr;
	
	public Function(String strFunct) {
		super(strFunct);
		expr = new ExpressionBuilder(strFunct).variable("x").build();
	}

	@Override
	public double calculate(double x) {
		expr.setVariable("x", x);
		return expr.evaluate();
	}
}
