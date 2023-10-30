package de.coord.java;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.plaf.synth.SynthSeparatorUI;

import de.coord.java.draw.CoordinateSystem;
import de.coord.java.draw.components.Axis;
import de.coord.java.draw.components.Axis.Type;
import de.coord.java.draw.components.Cube;
import de.coord.java.draw.components.Function;
import de.coord.java.draw.components.FunctionOwn;
import de.coord.java.draw.components.Line;
import de.coord.java.draw.components.Point;
import de.coord.java.graphics.Screen;
import de.coord.java.input.Keyboard;
import de.coord.java.input.Mouse;
import de.coord.java.interpreter.FunctionElements;
import de.position.java.Calc;

public class Coord extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static int width = 300 * 3;
	private static int height = 200 * 3;
	private static int scale = 1;
	
	private boolean running = false;
	
	private JFrame frame;
	private Screen screen;
	private Thread thread;
	
	private Keyboard keyboard;
	private Mouse mouse;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	public Coord() {
		frame = new JFrame();
		screen = new Screen(width, height);
		thread = new Thread(this, "Coord");
		
		keyboard = new Keyboard();
		mouse = new Mouse();
		
		addMouseListener(mouse);
		addMouseWheelListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(keyboard);
		setPreferredSize(new Dimension(width * scale, height * scale));
	}
	
	public synchronized void start() {
		running = true;
		thread.start();
		new Thread(() -> {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String line;
			
			try {
				while((line = input.readLine()) != null) {
					String[] cmd = line.split(":");
					if(cmd.length != 2) continue;
					try{
						double val = Double.valueOf(cmd[1]);
						switch (cmd[0]) {
							case "a": system.alpha = val; break;
							case "b": system.beta = val; break;
							case "g": system.gamma = val; break;
							default: continue;
						}
					} catch(NumberFormatException e) {
						System.err.println("wrong format");
						continue;
					}
				}
			} catch (Exception e) {
				System.err.println("read exception");
				e.printStackTrace();
			}
			System.err.println("escaped");
		}, "Input").start();
	}

	public synchronized void stop() {
		running = false;
	}
	
	/*Test*/
	CoordinateSystem system;
	
	public void run() {
		/*Test*/
		system = new CoordinateSystem(screen);
		system.add(new Axis(Type.X));
		system.add(new Axis(Type.Y));
		//system.add(new Point(1.5, 2));
		/*system.add(new Point(1, 2));
		system.add(new Point(2, 2));
		system.add(new Point(3, 2));
		system.add(new Point(4, 2));
		system.add(new Point(7, 5));
		system.add(new Point(10, 8));*/
		//system.add(new Function("0.1*x^3+x-2"));
		//system.add(new Function("0.27x+109"));
		//system.add(new Function("x^3"));
		//system.add(new Function("y=x*(1-(2*3))"));
		//system.add(new Function("0.5x+3"));
		//system.add(new Function("1/10x^3+x^2+3x"));
		//system.add(new Function("5sin(x*pi/12)"));
		//system.add(new Function("5cos(x*pi/12)"));
		//system.add(new Function("-x^2+2"));
		//system.add(new Function("0.2*(x-3)(x-10)^2"));
		
		/* Circle */
		//double radius = 5;
		//system.add(new Function("sqrt(" + radius + "^2-x^2)"));
		//system.add(new Function("-sqrt(" + radius + "^2-x^2)"));
		
		
		
		//system.add(new Function("1/3*(x^3+4*x^2-x-4)"));
		/*double angle1 = 2 * Math.PI * 80 / 360; //Math.PI / 6;
		system.add(new Line(
				new Point(Math.cos(angle1) * radius, Math.sin(angle1) * radius),
				new Point(-Math.cos(angle1) * radius, -Math.sin(angle1) * radius)
				));
		double angle2 = 5 * Math.PI / 6;
		system.add(new Line(
				new Point(Math.cos(angle2) * radius, Math.sin(angle2) * radius),
				new Point(-Math.cos(angle2) * radius, -Math.sin(angle2) * radius)
				));
		double angle3 = 2 * Math.PI / 6;
		system.add(new Line(
				new Point(Math.cos(angle3) * radius, Math.sin(angle3) * radius),
				new Point(-Math.cos(angle3) * radius, -Math.sin(angle3) * radius)
				));
		double angle4 = 4 * Math.PI / 6;
		system.add(new Line(
				new Point(Math.cos(angle4) * radius, Math.sin(angle4) * radius),
				new Point(-Math.cos(angle4) * radius, -Math.sin(angle4) * radius)
				));*/
		
		//system.add(new FunctionOwn("x*x"));
		
		/*Test Function -> WORKS!*/
		//FunctionElements f = new FunctionElements("1/4*(x-8)*(x-8)-1").initialize();
		/*FunctionElements f = new FunctionElements("1/10*x*x").initialize();
		for(int x = -20; x < 35; x++) {
			double y = f.solve(x).get();
			System.out.println("x = " + x + ", y = " + y);
			system.add(new Point(x, y));
			f.initialize();
		}*/
		
		/*Test Cube*/
		//system.add(new Cube(new Calc.Point3D(1, 1, 1), 4));
		
		/*Test U-Cube*/
		/*
		system.add(new Cube(new Calc.Point3D(0, 0, 0), 2));
		system.add(new Cube(new Calc.Point3D(0, 2, 0), 2));
		system.add(new Cube(new Calc.Point3D(0, 4, 0), 2));
		system.add(new Cube(new Calc.Point3D(2, 0, 0), 2));
		system.add(new Cube(new Calc.Point3D(4, 0, 0), 2));
		system.add(new Cube(new Calc.Point3D(4, 2, 0), 2));
		system.add(new Cube(new Calc.Point3D(4, 4, 0), 2));
		*/
		
		/*Test Pyramid*/
		
		system.add(new Cube(new Calc.Point3D(0, 0, 0), 2));
		system.add(new Cube(new Calc.Point3D(2, 0, 0), 2));
		system.add(new Cube(new Calc.Point3D(4, 0, 0), 2));
		system.add(new Cube(new Calc.Point3D(4, 0, -2), 2));
		system.add(new Cube(new Calc.Point3D(0, 2, 0), 2));
		system.add(new Cube(new Calc.Point3D(4, 0, 0), 2));
		
		/*system.add(new Cube(new Calc.Point3D(0, 2, 0), 2));
		system.add(new Cube(new Calc.Point3D(2, 2, 0), 2));
		system.add(new Cube(new Calc.Point3D(4, 2, 0), 2));
		system.add(new Cube(new Calc.Point3D(0, 4, 0), 2));
		system.add(new Cube(new Calc.Point3D(2, 4, 0), 2));
		system.add(new Cube(new Calc.Point3D(4, 4, 0), 2));
		system.add(new Cube(new Calc.Point3D(2, 2, 2), 2));*/
		
		/*Test Line m < 0*/
		//system.add(new Line(new Point(4, 4), new Point(5, -3)));
		
		/*Test Circle / Line*/
		/*
		Point m = new Point(4, 4);
		double angle = 0;
		double radius = 3;
		Point p = new Point(Math.cos(Math.toRadians(angle)) * radius + m.getX(), Math.sin(Math.toRadians(angle)) * radius + m.getY());
		Point p2 = new Point(Math.cos(Math.toRadians(angle + 80)) * radius + m.getX(), Math.sin(Math.toRadians(angle + 80)) * radius + m.getY());
		Point p3 = new Point(Math.cos(Math.toRadians(angle + 100)) * radius + m.getX(), Math.sin(Math.toRadians(angle + 100)) * radius + m.getY());
		Point p4 = new Point(Math.cos(Math.toRadians(angle + 260)) * radius + m.getX(), Math.sin(Math.toRadians(angle + 260)) * radius + m.getY());
		Point p5 = new Point(Math.cos(Math.toRadians(angle + 280)) * radius + m.getX(), Math.sin(Math.toRadians(angle + 280)) * radius + m.getY());
		Line l = new Line(m, p);
		system.add(m);
		system.add(p);
		system.add(p2);
		system.add(p3);
		system.add(p4);
		system.add(p5);
		system.add(l);
		system.add(new Line(m, p2));
		system.add(new Line(m, p3));
		system.add(new Line(m, p4));
		system.add(new Line(m, p5));
		system.add(new Line(new Point(4, 4), new Point(4, 7)));
		*/
		
		/*Test Line*/
		/*
		system.add(new Line(new Point(-5, 3), new Point(15, 3)));
		system.add(new Line(new Point(-5, 1), new Point(15, 1)));
		system.add(new Line(new Point(-5, 1), new Point(-5, 3)));
		system.add(new Line(new Point(15, 1), new Point(15, 3)));
		system.add(new Line(new Point(2, 3), new Point(2.5, 1)));
		system.add(new Line(new Point(-2, 1), new Point(-1.5, 3)));
		*/
		
		/*while (running) {
			//System.out.println("running..");
			
			update();
			
			
		}*/
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
	}
	
	private void update() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		/*Test*/
		system.draw();
		
		handleOffset();
		hanldeScale();
		system.alpha = mouse.a;
		system.beta = mouse.b;
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	private void handleOffset() {
		if(keyboard.up) screen.yOff--;
		if(keyboard.down) screen.yOff++;
		if(keyboard.left) screen.xOff++;
		if(keyboard.right) screen.xOff--;
	}
	
	private void hanldeScale() {
		if(mouse.rotation != 0) {
			int newScale = system.getScale() + mouse.rotation;
			if(newScale > 0) system.setScale(newScale);
			mouse.rotation = 0;
		}
	}
	
	public static void main(String[] args) {
		Coord coord = new Coord();
		coord.frame.setVisible(true);
		coord.frame.setResizable(false);
		coord.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//coord.frame.setLocationRelativeTo(null);
		coord.frame.setTitle("3D-Coord");
		
		coord.frame.add(coord);
		coord.frame.pack();
		
		coord.start();
	}
}
