/*
 * Class created for the Circle Tool
 */

import java.awt.Color;
import java.awt.Graphics;

public class Circle {
private int diameter,x,y;
private Color color;

	public Circle(int size, Color fill, int X, int Y)
	{
		diameter = size;
		color=fill;
		x=X;
		y=Y;
	}
	// the methods being used
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(x, y, diameter, diameter);
	}
	
	public void setDiameter(int size){
		diameter=size;
	}
	
	public void setColor(Color fill){
		color=fill;
	}
	
	public void setX(int X){
		x=X;
	}
	public void setY(int Y){
		y=Y;
	}
	
	public int getDiameter(){
		return diameter;
	}
	
	public Color getColor(){
		return color;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	

}