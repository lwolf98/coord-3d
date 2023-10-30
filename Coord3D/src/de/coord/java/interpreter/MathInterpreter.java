package de.coord.java.interpreter;

import java.util.List;

import de.coord.java.interpreter.arithmetic.Element;
import de.coord.java.interpreter.arithmetic.Number;
import de.coord.java.interpreter.arithmetic.Product;
import de.coord.java.interpreter.arithmetic.Sum;
import de.coord.java.interpreter.arithmetic.Variable;

public final class MathInterpreter {
	private final int FORMAT_COMPARATOR = 1;
	private final int FORMAT_ARITHMETIC_OPERATOR = 2;
	private final int FORMAT_VARIABLE = 3;
	
	private final String CONFIG_PATH = "/config.math";
	private List<String[]> config;
	
	public MathInterpreter() {
		config = ConfigReader.read(getClass().getResourceAsStream(CONFIG_PATH));
		//config.sort(new ConfigComparator());
		config.sort((o1, o2) -> o2[0].length() - o1[0].length());
		//System.out.println("Interpreter loaded");
	}
	
	public Element interpretU(String e) {
		for(String[] data : config) {
			if(data[0].equals(e)) {
				switch (Integer.parseInt(data[1])) {
					case FORMAT_COMPARATOR:
						
						break;
						
					case FORMAT_ARITHMETIC_OPERATOR:
						
						break;
						
					case FORMAT_VARIABLE:
						
						break;
		
					default:
						break;
				}
			}
		}
		
		return new Number(0, null);
	}
	
	public Element interpret(String e, FunctionElements function) {
		if(isNumeric(e)) return new Number(Double.parseDouble(e), function);
		if(e.equals("*") || e.equals("/")) return new Product(e, function);
		if(e.equals("+") || e.equals("-")) return new Sum(e, function);
		//if(e.contains("=") || e.contains("<") || e.contains(">")) return new Comparator(function);
		if(e.equals("x") || e.equals("y")) return new Variable(e, function);
		
		return null;
	}
	
	public String validateElement(String funct, int start) {
		for(String[] data : config) {
			String operator = data[0];
			String toCheck = funct.substring(start, start + getLength(funct, start, operator.length())); //i + operator.length());
			if(toCheck.equals(operator))
				return operator;
		}
		
		return getNumber(funct, start);
		//i += parts.get(i).length() - 1; ...interessant!
	}
	
	private String getNumber(String funct, int pos) {
		int c = 0;
		while((pos + c) < funct.length() && isNumeric(funct.charAt(pos + c)))
			c++;
		
		return funct.substring(pos, pos + c);
	}
	
	private boolean isNumeric(String s) {
		for(int i = 0; i < s.length(); i++)
			if(!isNumeric(s.charAt(i)))
				return false;
		
		return true;
	}
	
	/*clean up helper methods*/
	private boolean isNumeric(char c) {
		char[] valids = new char[] {'0','1','2','3','4','5','6','7','8','9','.'};
		for(int i = 0; i < valids.length; i++)
			if(c == valids[i]) return true;
		
		return false;
	}

	private int getLength(String funct, int pos, int operatorLength) {
		int dif = funct.length() - (pos + operatorLength);
		if(dif >= 0) return operatorLength;
		else return 1;
	}
	
	public static void main(String[] args) {
		MathInterpreter interpreter = new MathInterpreter();
		String num = interpreter.getNumber("y>=23", 3);
		System.out.println("done: " + num);
	}
}