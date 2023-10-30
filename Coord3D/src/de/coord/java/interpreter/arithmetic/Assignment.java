package de.coord.java.interpreter.arithmetic;

import de.coord.java.interpreter.FunctionElements;

public abstract class Assignment extends Element {
	private boolean positive = true;
	private boolean assigned = false;
	
	public Assignment() {
	}
	
	public Assignment(FunctionElements function) {
		super(function);
	}

	public void assign(Sum sum) {
		/* Typ-Auswertung hier oder lieber direkt in Sum, dass nur ein Bool für 
		 * positiv/negativ zurück kommt?
		 */
		switch (sum.getType()) {
			case Sum.TYPE_PLUS:
				positive = true;
				assigned = true;
				break;
				
			case Sum.TYPE_MINUS:
				positive = false;
				assigned = true;
				break;
		}
	}
	
	public boolean isAssigned() {
		return assigned;
	}
	
	public boolean isPositive() {
		return positive;
	}
}
