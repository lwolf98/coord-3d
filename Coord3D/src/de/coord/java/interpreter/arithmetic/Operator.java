package de.coord.java.interpreter.arithmetic;

import de.coord.java.interpreter.FunctionElements;

public abstract class Operator extends Element {
	public Operator(FunctionElements function) {
		super(function);
	}

	public abstract void operate();
}
