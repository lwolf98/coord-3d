package de.coord.java.interpreter;

import java.util.ArrayList;
import java.util.List;

import de.coord.java.interpreter.arithmetic.Assignment;
import de.coord.java.interpreter.arithmetic.Element;
import de.coord.java.interpreter.arithmetic.Number;
import de.coord.java.interpreter.arithmetic.Product;
import de.coord.java.interpreter.arithmetic.Sum;
import de.coord.java.interpreter.arithmetic.Variable;

public class FunctionElements extends Assignment {
	private List<Element> innerFunction;
	private String funct;
	private boolean initialized = false;
	
	public FunctionElements(String funct) {
		this.funct = funct;
	}
	
	/*nach Möglichkeit in Konstruktor übernehmen, Problem bei Übergabe von 'this'*/
	public FunctionElements initialize() {
		
		/*TODO: TEST - evtl. falsch!!*/
		/*UPDATE: Falsche Zuweisung*/
		//function = this;
		/*END TEST*/
		
		MathInterpreter interpreter = new MathInterpreter();
		innerFunction = new ArrayList<Element>();
		for(int i = 0; i < funct.length(); i++) {
			Element e;
			if(funct.charAt(i) == '(') {
				String inner = "";
				
				/*evtl. in Methode 'String getInner(String funct, int start)'*/
				int start = i + 1;
				int bracketCount = 0;
				while (!(funct.charAt(i) == ')' && bracketCount == 0)) {
					if(funct.charAt(i) == '(') bracketCount++;
					i++;
					if(funct.charAt(i) == ')') bracketCount--;
				}
				inner = funct.substring(start, i);
				/*end getInner*/
				
				e = new FunctionElements(inner).initialize();
				((FunctionElements)e).function = this;
				
			} else {
				String eStr = interpreter.validateElement(funct, i);
				i += eStr.length() - 1;
				e = interpreter.interpret(eStr, this);
			}
			
			//function.set(index, e);
			innerFunction.add(e);
		}
		handleAssignments();
		
		initialized = true;
		return this;
	}

	public void handleAssignments() {
		for(int i = 0; i < innerFunction.size(); i++) {
			Element e = innerFunction.get(i);
			
			if(e instanceof Assignment) {
				if(e.before() instanceof Sum) {
					((Assignment)e).assign((Sum)e.before());
					/*remove evtl. in assign()?*/
					e.before().remove();
				} else {
					((Assignment) e).assign(new Sum(Sum.TYPE_PLUS, this));
				}
			}
		}
	}
	
	public boolean elementsAreAssigned() {
		for(Element e : innerFunction) {
			if(e instanceof Assignment) {
				Assignment asg = (Assignment)e;
				if(asg instanceof FunctionElements)
					if(!((FunctionElements)asg).elementsAreAssigned())
						return false;
				
				if(!asg.isAssigned())
					return false;
			}
		}
		return true;
	}
	
	public Number solve(double x) {
		for(int i = 0; i < innerFunction.size(); i++) {
			Element e = innerFunction.get(i);
			
			if(e instanceof Product) {
				/*vor und nach Produkt-Operator kann nur ein Assignment stehen, sont
				 * mathematischer Fehler*/
				
				Number num1 = numberFromAssignment((Assignment)e.before(), x);
				Number num2 = numberFromAssignment((Assignment)e.next(), x);
				
				//somehow doesn't refer to the Element-List
				//e = ((Product)e).operate(num1, num2);
				//workaround..:
				Number res = ((Product)e).operate(num1, num2);
				
				/*remove() evtl. in operate()?*/
				e.before().remove();
				e.next().remove();
				i--; //zurücksetzen wegen remove()
				
				innerFunction.set(innerFunction.indexOf(e), res);
			}
		}
		
		/*Funct sollte nur noch Numbers enthalten*/
		Element e = innerFunction.get(0);
		if(e instanceof Variable)
			((Variable)e).set(x);
		
		while(e.next() != null){
			if(e.next() instanceof Variable)
				((Variable)e.next()).set(x);
			
			e = ((Number)e).add((Number)e.next());
			e.next().remove();
		}
		
		/*gibt die einzig übrige Number zurück*/
		return (Number)innerFunction.get(0);
	}
	
	/*Zugriff auf Element-Liste*/
	public List<Element> getElements() {
		return innerFunction;
	}
	
	private Number numberFromAssignment(Assignment e, double x) {
		if(e instanceof Variable) {
			return ((Variable)e).set(x);
		} else if(e instanceof Number) {
			return (Number)e;
		} else if(e instanceof FunctionElements) {
			return ((FunctionElements)e).solve(x);
		}
		
		/*Null ist nur durch mathematischen Fehler möglich*/
		return null;
	}
	
	
	/*if(e.before() instanceof Variable) {
		num1 = ((Variable)e.before()).set(x);
	} else if(e.before() instanceof Number) {
		num1 = (Number)e.before();
	} else if(e.before() instanceof FunctionElements) {
		return ((FunctionElements)e.before()).solve(x);
	}*/
	
	/*public int execute(double x) {
		Hashtable<Integer, Element> calculation = copy();
		while(calculation.size() > 1) {
			Hashtable<Integer, Element> newFunction = new Hashtable<Integer, Element>();
			int count = 0;
			for(int i = 0; i < calculation.size(); i++) {
				Element e = calculation.get(i);
				if(e instanceof Variable && ((Variable)e).getName().equals("x"))
					((Variable)e).set(x);
				
				if(e instanceof MathOperator && (calculation.get(i - 1) instanceof Number && (calculation.get(i + 1) instanceof Number))) {
					newFunction.put(count, ((MathOperator)e).operate((Number)calculation.get(i - 1), (Number)calculation.get(i + 1)));
					count++;
				}
				
				calculation = newFunction;
			}
		}
		
		
		
		if(initialized) {
			
		} else {
			return -1;
		}
	}*/
	
	/*private Hashtable<Integer, Element> copy() {
		Hashtable<Integer, Element> clone = new Hashtable<Integer, Element>();
		
		for(int i = 0; i < function.size(); i++)
			clone.put(i, function.get(i));
		
		return clone;
	}*/
}
