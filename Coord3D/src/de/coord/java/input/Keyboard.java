package de.coord.java.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	public boolean up, down, left, right;

	public void update(KeyEvent e, boolean state) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) up = state;
		if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) down = state;
		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) left = state;
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) right = state;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		update(e, true);
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		update(e, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
