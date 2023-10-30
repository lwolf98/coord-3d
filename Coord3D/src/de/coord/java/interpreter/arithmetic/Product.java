package de.coord.java.interpreter.arithmetic;

import de.coord.java.interpreter.FunctionElements;

public class Product extends Element implements MathOperator {
	private String type;
	
	public Product(String type, FunctionElements function) {
		super(function);
		
		/*TODO: evtl. enum*/
		this.type = type;
	}

	public void operate() {
		
	}

	/*darf nur mit assignten Werten rechnen*/
	/*evtl. operate() statt operate(num, num) verwenden und
	 * next() und before() hier ermitteln*/
	public Number operate(Number num1, Number num2) {
		//TODO: Assignment-Politik, Number negative Zahlen adden..
		Number res;
		String sumType = num1.isPositive() == num2.isPositive() ? "+" : "-";
		
		switch (type) {
			case "*":
				res = new Number(num1.get() * num2.get(), num1.function);
				//evtl. unnötig
				res.assign(new Sum(sumType, num1.function));
				break;
				
			case "/":
				res = new Number(num1.get() / num2.get(), num1.function);
				//evtl. unnötig
				res.assign(new Sum(sumType, num1.function));
				break;
				
			default:
				res = null;
				break;
		}
		return res;
	}
}
