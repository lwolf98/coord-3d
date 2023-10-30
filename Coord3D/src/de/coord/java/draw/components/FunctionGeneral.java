package de.coord.java.draw.components;

import de.coord.java.draw.CoordinateSystem;

public abstract class FunctionGeneral extends Component {
	protected String expr;
	
	public FunctionGeneral(String expr) {
		this.expr = expr;
	}
	
	public abstract double calculate(double x);

	@Override
	public void draw(CoordinateSystem system) {
		system.getScreen().renderFunction(system, this);
	}
}
