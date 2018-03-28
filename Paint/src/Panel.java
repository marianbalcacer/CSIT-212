	/*
	 * JPanelis created to show where the results will appear.

	 * Function statement so the tools will work on the canvas. 
	 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
public class Panel extends JPanel implements MouseListener, MouseMotionListener {
	private int x0, y0, x1, y1;
	private int panelwidth; //the weight of the panel
	private int panelHeight=30; //the height of the panel 
	
	private int Cx,Cy;// current x y 
	private int pensize=3;// default pen size
	
	private Color c =Color.black; //default color 
	private int function=0;//function is the mode, allows the user to click on each function box 
	
	

	
	/*
	 * ArrayList is created to obtain the classes/objects of the shape 
	 */
	
	ArrayList<Circle> circles = new ArrayList<Circle>();
	ArrayList<Square> squares = new ArrayList<Square>();
	ArrayList<PenTool> pentool = new ArrayList<PenTool>();
	
	
	public Panel() {

		setPreferredSize(new Dimension(650, 500)); //size of the frame 
		setBackground(Color.WHITE);

		addMouseListener(this);
		addMouseMotionListener(this); } //mouse commands 

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			panelwidth = this.getWidth() / 10;//this will allow for 10 function boxes to appear, will work the same as buttons 

			// draw the empty tool panel
			g.setColor(Color.black);
			g.drawRect(0, 0, this.getWidth() - 1, panelHeight + 1);
			g.setColor(Color.white);
			g.fillRect(1, 1, this.getWidth() - 1, panelHeight);
			// The color boxes 
			g.setColor(Color.BLACK);
			g.fillRect(1, 1, panelwidth, panelHeight); // it will get the size of the window frame and the height of the panel to make each box
			g.setColor(Color.RED);
			g.fillRect((panelwidth * 1), 1, panelwidth, panelHeight);
			g.setColor(Color.YELLOW);
			g.fillRect((panelwidth * 2), 1, panelwidth, panelHeight);
			g.setColor(Color.BLUE);
			g.fillRect((panelwidth * 3), 1, panelwidth, panelHeight);
			g.setColor(Color.GREEN);
			g.fillRect((panelwidth * 4), 1, panelwidth, panelHeight); // each box will be equal sizes 
			

		/*
		 * Function Tool Panels 
		 * 
		 */
			
			// Free Pen Tool 
			g.setColor(Color.BLACK); // Default color will be black 
			g.drawRect((panelwidth * 5), 0, panelwidth, panelHeight + 1); //will be the fifth section 
			g.drawString("Pen",(panelwidth * 5 + panelwidth / 4), panelHeight / 2); //this arranges the string text

			// Circle Tool
			g.drawRect(panelwidth * 6, 0, panelwidth, panelHeight + 1);
			if (panelwidth * 7 - panelwidth * 6 >= panelHeight)
			g.drawString("Circle", panelwidth * 6 + panelwidth/5, panelHeight /2);// this will place the text within this sizes no matter what
			
			// Square Tool
			g.drawRect ( panelwidth * 7, 0, panelwidth, panelHeight+1);
			if (panelwidth * 8 - panelwidth * 7 >= panelHeight)
			g.drawString("Square",panelwidth * 7 + panelwidth / 6, panelHeight / 2);
			
			
			// 15pt Free Pen Tool
			g.drawRect(panelwidth * 8, 0, panelwidth, panelHeight+1); //This is the different Stroke size for the pen tool
			if (panelwidth * 9 - panelwidth * 8 >= panelHeight)
			g.drawString("15pt Pen",(panelwidth* 8)+ panelwidth/7,panelHeight / 2);
			
			//Clear Tool
			g.drawRect(panelwidth * 9, 0, panelwidth, panelHeight + 1); //This will clear the whole canvas
			g.drawString("Clear", (panelwidth*9)+panelwidth/8,panelHeight / 2);
			
			
			
			// This is the for loops to allow the ArrayList objects to appear 
			for (Circle circle : circles) {
					circle.draw(g);
			}
			for (PenTool pdot : pentool) { //will appear as dots but as you drag, the pen will form similar to PAINT 
					pdot.draw(g);
			}
			for (Square sq : squares) {
					sq.draw(g);
			}


		}

		@Override
		public void mouseClicked(MouseEvent e) {
			/*
			 * These are the If statements to allow the the area of the 
			 * function to work like a button, to stay within each area
			 * Window can be re-sizable since the function panel will take the width and height regardless 
			 *
			 */
			
			if (e.getX() > 0 && e.getX() < (panelwidth) && e.getY() > 1
				&& e.getY() < panelHeight) { //This will get the color black within the border of panel box Black
				c = Color.BLACK;
				

			}
			
			else if (e.getX() > (panelwidth*1) && x1 < (panelwidth*2) 
					&& e.getY() > 1 && e.getY() < panelHeight) {
					c = Color.RED;
				
			} 
			
			else if (e.getX() > (panelwidth * 2) && x1 < (panelwidth * 3) 
					&& e.getY() > 1 && e.getY() < panelHeight) {
					c = Color.YELLOW;
				
			} 
			
			else if (e.getX() > (panelwidth * 3) && x1 < (panelwidth * 4) 
					&& e.getY() > 1 && e.getY() < panelHeight) {
					c = Color.BLUE;
			} 
			
			else if (e.getX() > (panelwidth * 4) && x1 < (panelwidth * 5) 
					&& e.getY() > 1 && e.getY() < panelHeight) {
					c = Color.GREEN;
			
			}
			
			/* 
			 * These statements will allow the user to 
			 * click on each function 
			 */
			
			else if (e.getX() > (panelwidth * 5) && x1 < (panelwidth * 6)
					&& e.getY() > 1 && e.getY() < panelHeight) { //This will get the function the PenTool within the panel box of Pen
					function = 0;
					pensize=3;
			
			}
			
			else if (e.getX() > (panelwidth * 8) && e.getX() < (panelwidth * 9) 
					&& e.getY() > 1 && e.getY() < panelHeight) {// The 15pt pen stroke 
					function=0;
					pensize=15;
							} 
			
			else if (e.getX() > (panelwidth * 6) && e.getX() < (panelwidth * 7) 
					&& e.getY() > 1 && e.getY() < panelHeight) { //Circle panel 
					function = 1;
			
			
			} 
			
			else if (e.getX() > (panelwidth * 7) && e.getX() < (panelwidth * 8)
					&& e.getY() > 1 && e.getY() < panelHeight) {//Square pane;
					function = 2;
		
			}
			/*
			 * Clear button statements 
			 */
			
			
			else if (e.getX() > panelwidth * 9 && e.getX() < panelwidth * 10
					&& e.getY() > 1 && e.getY() < panelHeight) { //click within the Clear panel it will clear
					circles.clear();
					squares.clear();
					pentool.clear();
			}

				repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			/*
			 * Function statement for shapes
			 * Will not go onto tool panels also
			 */
			if (function != 0) {
				x0 = e.getX();
				if (e.getY() < 100) {
					y0 = 100;
				} else {
					y0 = e.getY();
				}
				repaint();
			}
		}

		@Override

		public void mouseReleased(MouseEvent e) {
			// get mouse position on release
			x1 = e.getX();
			y1 = e.getY();

			// these statements will set to draw shape
			if (function != 0) {
				if ((x1 - x0 > 0)) {
					if (function == 1)
						circles.add(new Circle(x1 - x0, c, x0, y0));
					else if (function == 2)
						squares.add(new Square(x0, y0, x1 - x0, y1 - y0, c));
				} else {
					if (function == 1)
						circles.add(new Circle(x0 - x1, c, x0, y0));
					else if (function == 2)
						squares.add(new Square(x0, y0, x0 - x1, y0 - y1, c));
				}
					
				repaint();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {// Mouse position when dragging
			
			if (e.getX() > this.getWidth()) { // Canvas Area for the shapes to be drawn on
				
				x1 = this.getWidth(); //Area of the Canvas
				
			} 
			else if (e.getX() < 0) {
				x1 = 0;
				
			} 
			else {
				x1 = e.getX();
			}
			if (e.getY() < panelHeight + 1) {
				y1 = panelHeight;
				
			} 
			else if (e.getY() > this.getHeight()) {
				y1 = this.getHeight() - pentool.get(0).getDiameter();
			} 
			else {
				y1 = e.getY();
			}

			// Current mouse position for the pen tool
			if (function == 0) {
				pentool.add(new PenTool(pensize, c,Cx,Cy) );
				
			} 
				Cx = e.getX();
				Cy = e.getY();
	
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			//Mouse position 
			Cx = e.getX();
			Cy = e.getY();
			repaint();
		}

		
}
