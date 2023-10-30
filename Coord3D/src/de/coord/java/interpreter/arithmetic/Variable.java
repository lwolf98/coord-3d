package de.coord.java.interpreter.arithmetic;

import de.coord.java.interpreter.FunctionElements;

public class Variable extends Number {
	private boolean set = false;
	private String name;

	public Variable(String name, FunctionElements function) {
		super(-1, function); //wird overriden benutzt? ja!
		this.name = name;
	}
	
	public boolean isSet() {
		return set;
	}

	public Number set(double value) {
		super.set(value); //this.value = value;
		set = true;
		return this;
	}
	
	public double get() {
		if(set) return super.get(); //return value;
		else return 0;
	}
	
	public String getName() {
		return name;
	}
}
