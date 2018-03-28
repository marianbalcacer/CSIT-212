/*
 *Class created for the Square Tool
 */

import java.awt.Color;
import java.awt.Graphics;

public class Square {
	private int x, y, width, height;
	private Color color;

	public Square(int X, int Y, int xwidth, int yheight, Color fill) {
		color = fill;
		x = X;
		y = Y;
		width = xwidth;
		height = yheight;
	}
	// the methods being used
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, width);
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}

	public void setColor(Color fill) {
		color = fill;
	}

	public void setX(int X) {
		x = X;
	}

	public void setY(int Y) {
		y = Y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}