package de.coord.java.draw;

import java.util.ArrayList;
import java.util.List;

import de.coord.java.draw.components.Component;
import de.coord.java.graphics.Screen;

public class CoordinateSystem {
	public static final int CLR_TRANSPARENT = 0xff00ff;
	public static final int CLR_DEFAULT = 0x000000;
	
	private Screen screen;
	private List<Component> components;
	private int scale = 15;
	
	public double alpha = 0;
	public double beta = 0;
	public double gamma = 0;
	
	public CoordinateSystem(Screen screen) {
		this.screen = screen;
		components = new ArrayList<Component>();
	}
	
	public static int[] getDrawingArea(int size) {
		return getDrawingArea(size, size);
	}
	
	public static int[] getDrawingArea(int width, int height) {
		int[] area = new int[width * height];
		
		for(int i = 0; i < area.length; i++) {
			area[i] = CLR_TRANSPARENT;
		}
		
		return area;
	}
	
	public void add(Component c) {
		components.add(c);
	}
	
	public void draw() {
		for(Component c : components) {
			c.draw(this);
		}
		screen.reversePixels();
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	public int getScale() {
		return scale;
	}
	
	/*DEBUG*/
	public List<Component> getComponents() {
		return components;
	}
}
