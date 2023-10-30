package de.coord.java.interpreter.arithmetic;

import de.coord.java.interpreter.FunctionElements;

public class Number extends Assignment {
	protected double value;
	
	public Number(double value, FunctionElements function) {
		super(function);
		
		//evtl. bessere M�glichkeit
		//assign() gleich hier verwenden?
		this.value = value >= 0 ? value : -1 * value;
	}

	public Number set(double value) {
		this.value = value;
		return this;
	}
	
	public double get() {
		if(isAssigned()) {
			return isPositive() ? value : -1 * value;
		}
		//evtl. throw Error
		return 0;
	}
	
	/*evtl. nicht ben�tigt*/
	public double getUnsigned() {
		return value;
	}
	
	/*evtl. hier next().remove() statt in solve()*/
	public Number add(Number num) {
		/*TODO: Variable muss besser auf gesetted gepfr�ft werden!
		 * => aktuell mit elementsAreAssigned() gel�st*/
		/*anstelle von +1 wird positive (boolean) verrechnet*/
		set(+1 * get() + +1 * num.get());
		return this;
	}
}
