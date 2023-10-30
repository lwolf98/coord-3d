package de.coord.java.graphics;

import de.coord.java.draw.CoordinateSystem;
import de.coord.java.draw.components.Axis;
import de.coord.java.draw.components.Axis.Type;
import de.coord.java.draw.components.FunctionGeneral;
import de.coord.java.draw.components.Line;
import de.coord.java.draw.components.Point;

public class Screen {
	public int[] pixels;
	private int width;
	private int height;
	
	public int xOff = 0;
	public int yOff = 0;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void renderPoint(CoordinateSystem system, Point point, int[] area) {
		int size = (int)Math.sqrt(area.length);
		int xStart = (int)Math.round(point.getX() * system.getScale()) - (size - 1) / 2;
		int yStart = (int)Math.round(point.getY() * system.getScale()) - (size - 1) / 2;
		
		for(int yCur = yStart; yCur < yStart + size; yCur++) { //evtl. y += system.getScale(); ?
			for(int xCur = xStart; xCur < xStart + size; xCur++) {
				int x = xCur + xOff;
				int y = yCur + yOff;
				if(y < 0 || y >= height || x < 0 || x >= width) continue;
				
				int value = area[(xCur - xStart) + (yCur - yStart) * size];
				if(value != CoordinateSystem.CLR_TRANSPARENT)
					pixels[x + y * width] = value;
			}
		}
	}
	
	public void renderLine(CoordinateSystem system, Line line) {
		double xStart = line.getX() * system.getScale();
		double width = Math.abs((line.getP1().getX() - line.getP2().getX()) * system.getScale());
		double height = Math.abs((line.getP1().getY() - line.getP2().getY()) * system.getScale());
		
		for(double x = xStart; x <= xStart + width; x++) {
			double y = line.getM() * x + line.getT() * system.getScale();
			double yTo = line.getM() * (x + 1) + line.getT() * system.getScale();
			
			//Override values for special case m = Infinity
			if(line.getM() == Double.POSITIVE_INFINITY || line.getM() == Double.NEGATIVE_INFINITY) {
				y = line.getY() * system.getScale();
				yTo = line.getY() * system.getScale() + height;
			}
			int xx = (int)Math.round(x) + xOff;
			int yy = (int)Math.round(y) + yOff;
			int yyTo = (int)Math.round(yTo) + yOff;
			do {
				if(yy < yyTo) yy++;
				else if(yy > yyTo) yy--;
				
				if(yy < 0 || yy >= this.height || xx < 0 || xx >= this.width) continue;
				
				pixels[xx + yy * this.width] = CoordinateSystem.CLR_DEFAULT;
				
				if(yy < yyTo) {
					if(yy >= line.getY() * system.getScale() + height + yOff) break;
				} else if(yy > yyTo) {
					if(yy <= line.getY() * system.getScale() + yOff) break;
				}
				//if(yy >= line.getP2().getY() * system.getScale() + yOff) break;
			} while(yy != yyTo);
		}
	}
	
	/*Old version with unhandled m problem*/
	/*public void renderLine(CoordinateSystem system, Line line) {
		double xStart = line.getX() * system.getScale();
		double width = Math.abs((line.getP1().getX() - line.getP2().getX()) * system.getScale());
		
		for(double x = xStart; x <= xStart + width; x++) {
			double y = line.getM() * x + line.getT() * system.getScale();
			double yTo = line.getM() * (x + 1) + line.getT() * system.getScale();
			
			int xx = (int)Math.round(x) + xOff;
			int yy = (int)Math.round(y) + yOff;
			int yyTo = (int)Math.round(yTo) + yOff;
			do {
				if(yy < yyTo) yy++;
				else if(yy > yyTo) yy--;
				
				if(yy < 0 || yy >= this.height || xx < 0 || xx >= this.width) continue;
				
				/*Probleme beim Runden lösen...*/
				/*double yP1 = line.getP1().getY() * system.getScale();
				double yP2 = line.getP2().getY() * system.getScale();
				if(yP2 > yP1) {
					if (yy > (int)Math.round(yP2)) continue;
				} else if(yP1 > yP2) {
					if (yy > (int)Math.round(yP1)) continue;
				}*
				
				pixels[xx + yy * this.width] = CoordinateSystem.CLR_DEFAULT;
			} while(yy != yyTo);
		}
	}*/
	
	/*with rounded int values*/
	/*public void renderLine(CoordinateSystem system, Line line) {
		int xStart = (int)Math.round(line.getX() * system.getScale());
		//int yStart = (int)Math.round(line.getY() * system.getScale());
		
		int width = Math.abs(toPixel(line.getP1().getX() - line.getP2().getX(), system.getScale()));
		//int height = Math.abs(toPixel(line.getP1().getY() - line.getP2().getY(), system.getScale()));
		
		for(int x = xStart; x < xStart + width; x++) {
			//int y = (int)Math.round(line.getM() * x + toPixel(line.getT(), system.getScale())) + yOff;
			int y = (int)Math.round(line.getM() * x + line.getT() * system.getScale()) + yOff;
			int xx = x + xOff;
			
			int yTo = (int)Math.round(line.getM() * (x + 1) + line.getT() * system.getScale()) + yOff;
			do {
				if(y < yTo) y++;
				else if(y > yTo) y--;
				
				if(y < 0 || y >= this.height || xx < 0 || xx >= this.width) continue;
				
				/*Probleme beim Runden lösen...*/
				/*double yP1 = line.getP1().getY();
				double yP2 = line.getP2().getY();
				if(yP2 > yP1) {
					if (y < yP1 || y > yP2) continue;
				} else if(yP1 > yP2) {
					if (y < yP2 || y > yP1) continue;
				}
				
				pixels[xx + y * this.width] = CoordinateSystem.CLR_DEFAULT;
			} while(y != yTo);
		}
		
		/*for(int x = xStart; x < xStart + width; x++) {
			int y = (int)Math.round(line.getM() * x + toPixel(line.getT(), system.getScale())) + yOff;
			int xx = x + xOff;
			
			if(y < 0 || y >= this.height || xx < 0 || xx >= this.width) continue;
			pixels[xx + y * this.width] = CoordinateSystem.CLR_DEFAULT;
		}
	}*/
	
	/*ÜBERARBEITEN*/
	public void renderAxis(CoordinateSystem system, Axis axis) {
		int size = 0;
		if(axis.getType() == Type.X) size = width;
		if(axis.getType() == Type.Y) size = height;
		if(axis.getType() == Type.Z) ; //???
		
		/*for(int i = 0; i < size; i++) {
			if(axis.getType() == Type.X) {
				//if(yOff - 1 < 0 || yOff + 1 >= height) continue;
				if(yOff < 0 || yOff >= height) continue;
				pixels[i + yOff * width] = 0;
				/*if(i % system.getScale() == 0) {
					pixels[(i + (xOff % system.getScale())) + (yOff + 1) * width] = 0;
					pixels[(i + (xOff % system.getScale())) + (yOff - 1) * width] = 0;
				}/
			}
			if(axis.getType() == Type.Y) {
				//if(xOff - 1 < 0 || xOff + 1 >= width) continue;
				if(xOff < 0 || xOff >= width) continue;
				pixels[xOff + i * width] = 0;
				/*if(i % system.getScale() == 0) {
					pixels[(xOff + 1) + (i + (yOff % system.getScale())) * width] = 0;
					pixels[(xOff - 1) + (i + (yOff % system.getScale())) * width] = 0;
				}/
			}
		}*/
		
		//int ret = xOff % system.getScale();
		//int[] area = getAxisArea(system.getScale(), ret);
		int[] area;

		for(int i = 0; i < size; i++) {
			if(axis.getType() == Type.X) {
				area = getAxisArea(system.getScale(), xOff % system.getScale());
				for(int ay = 0; ay < 3; ay++) {
					for(int ax = 0; ax < area.length / 3; ax++) {
						if(area[ax + ay * area.length / 3] == 0xff00ff) continue;
						if((yOff + ay - 1) < 0 || (yOff + ay - 1) >= height) continue;
						if(i + ax >= width) continue;
						pixels[(i + ax) + (yOff + ay - 1) * width] = area[ax + ay * area.length / 3];
					}
				}
				i += system.getScale() - 1;
			}
			
			if(axis.getType() == Type.Y) {
				area = getAxisArea(system.getScale(), yOff % system.getScale());
				for(int ay = 0; ay < 3; ay++) {
					for(int ax = 0; ax < area.length / 3; ax++) {
						if(area[ax + ay * area.length / 3] == 0xff00ff) continue;
						if((ax + i) >= height) continue;
						if((xOff + ay - 1) < 0 || (xOff + ay - 1) >= width) continue;
						pixels[(xOff + ay - 1) + (ax + i) * width] = area[ax + ay * area.length / 3];
					}
				}
				i += system.getScale() - 1;
			}
		}
		
		/*int[] area;
		int ret = 0;
		if(axis.getType() == Type.X)
			ret = xOff % system.getScale();
		else if(axis.getType() == Type.Y)
			ret = yOff % system.getScale();
		
		area = getAxisArea(system.getScale(), ret);
		
		for(int i = 0; i < size; i++) {
			for(int ay = 0; ay < 3; ay++) {
				for(int ax = 0; ax < system.getScale(); ax++) {
					if(area[ax + ay * area.length / 3] == 0xff00ff) continue;
					int x = 0;
					int y = 0;
					if(axis.getType() == Type.X) {
						x = i + ax;
						y = yOff + ay - 1;
					} else if(axis.getType() == Type.Y) {
						x = xOff + ay - 1;
						y = ax + i;
					}
					if(x < 0 || x >= width || y < 0 || y >= height) continue;
					pixels[x + y * width] = area[ax + ay * area.length / 3];
				}
			}
		}*/
	}
	
	private int[] getAxisArea(int scale, int ret) {
		int[] area = new int[scale * 3];
		int index;
		if(ret >= 0) index = ret;
		else index = scale + ret;
		
		for(int i = scale * 1; i < scale * 2; i++)
			area[i] = 0;
		
		for(int i = 0; i < scale; i++) {
			if(i == index) {
				area[i + 0 * scale] = 0;
				area[i + 2 * scale] = 0;
			} else {
				area[i + 0 * scale] = 0xff00ff;
				area[i + 2 * scale] = 0xff00ff;
			}
		}
		
		return area;
	}
	
	/*public void renderFunctionOwn(CoordinateSystem system, FunctionOwn function) {
		for(int x = -xOff; x < -xOff + this.width; x++) {
			//int yy = (20 * system.getScale()) + yOff;
			double xCoord = (double)x / system.getScale();
			//double yCoord = 3 * xCoord * xCoord * xCoord + 1 / 2 * xCoord * xCoord - 2 * xCoord + 5;
			//double yCoord = Math.sin(xCoord * Math.PI / 6);
			//double yCoord = Math.tan(xCoord * Math.PI / 6);
			double yCoord = function.calculate(xCoord);
			
			
			int y = (int)Math.round(yCoord * system.getScale());
			
			int xx = x + xOff;
			int yy = y + yOff;
			if(yy < 0 || yy >= this.height) continue;
			pixels[xx + yy * this.width] = CoordinateSystem.CLR_DEFAULT;
		}
	}*/
	
	public void renderFunction(CoordinateSystem system, FunctionGeneral function) {
		for(int x = -xOff; x < -xOff + this.width; x++) {
			double xCoord = (double)x / system.getScale();
			double yCoord = function.calculate(xCoord);
			double yToCoord = function.calculate((double)(x + 1) / system.getScale());
			
			int y = (int)Math.round(yCoord * system.getScale());
			int yTo = (int)Math.round(yToCoord * system.getScale());
			
			int xx = x + xOff;
			int yy = y + yOff;
			int yyTo = yTo + yOff;
			
			do {
				if(yy < yyTo) yy++;
				else if(yy > yyTo) yy--;
				
				if(yy < 0 || yy >= this.height) continue;
				pixels[xx + yy * this.width] = CoordinateSystem.CLR_DEFAULT;
			} while(yy != yyTo);
		}
	}
	
	public void reversePixels() {
		int[] reversedPixels = new int[pixels.length];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				reversedPixels[x + y * width] = pixels[x + (height - y - 1) * width];
			}
		}
		pixels = reversedPixels;
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffffff;
		}
	}
	
	private static int toPixel(double d, int scale) {
		return (int)Math.round(d * scale);
	}
}
