package de.coord.java.interpreter.arithmetic;

import de.coord.java.interpreter.FunctionElements;

/**
 * 
 * TODO: Wird Sum noch benötigt?
 *
 */
public class Sum extends Element implements MathOperator {
	public static final String TYPE_PLUS = "+";
	public static final String TYPE_MINUS = "-";
	
	//evtl. boolean für positive? nehmen
	private String type;
	
	public Sum(String type, FunctionElements function) {
		super(function);
		
		/*evtl. enum*/
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	/*kommt raus*/
	public Number operate(Number num1, Number num2) {
		return null;
	}
}
