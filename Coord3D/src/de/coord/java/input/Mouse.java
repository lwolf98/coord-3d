package de.coord.java.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
	public int rotation;
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("pressed");
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("released");
		xDrag = -1;
		yDrag = -1;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		rotation = e.getWheelRotation();
	}

	private int xDrag = -1;
	private int yDrag = -1;
	public int a = 0, b = 0;
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(xDrag != -1)
			b = b + (int)Math.round((e.getX() - xDrag)/2.5);
		
		if(yDrag != -1)
			a = a + (int)Math.round((e.getY() - yDrag)/2.5);
		
		xDrag = e.getX();
		yDrag = e.getY();
		
		System.out.println("alpha: " + a + " beta: " + b);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
