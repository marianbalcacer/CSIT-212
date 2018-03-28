/*
 * JFrame. This is the main method. 
 * Allows User to interact with the program 
 * The color boxes/ function tool boxes acts as buttons
 * The Pen Tool will be the default tool 
 */
import javax.swing.JFrame;

public class  Paint extends JFrame {
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().add(new Panel());
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
	}

}