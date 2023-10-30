package de.coord.java.interpreter.arithmetic;

import java.util.List;

import de.coord.java.interpreter.FunctionElements;

public abstract class Element {
	protected FunctionElements function;
	
	public Element() {
	}
	
	public Element(FunctionElements function) {
		this.function = function;
	}
	
	/*before() und next() geben null zurück, wenn Element Erstes bzw. Letztes ist*/
	public Element before() {
		List<Element> elements = function.getElements();
		try {
			return elements.get(elements.indexOf(this) - 1);
		} catch(IndexOutOfBoundsException ex) {
			return null;
		}
	}
	
	public Element next() {
		List<Element> elements = function.getElements();
		try {
			return elements.get(elements.indexOf(this) + 1);
		} catch(IndexOutOfBoundsException ex) {
			return null;
		}
	}
	
	/*entfernt sich selbst aus function*/
	public void remove() {
		List<Element> elements = function.getElements();
		int ind = elements.indexOf(this);
		elements.remove(ind);
	}
}
