package de.coord.java.draw.components;

import de.coord.java.interpreter.FunctionElements;

public class FunctionOwn extends FunctionGeneral {
	private FunctionElements function;
	
	public FunctionOwn(String strFunct) {
		super(strFunct);
		function = new FunctionElements(strFunct).initialize();
	}
	
	@Override
	public double calculate(double x) {
		double result = function.solve(x).get();
		function.initialize();
		return result;
	}
}
