/*
 * Class created for the Pen Tool
 */

import java.awt.Color;
import java.awt.Graphics;

public class PenTool {


private int diameter,x,y;
private Color color;


	public PenTool(int pensize,Color fill, int X, int Y){
		diameter=pensize;
		
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