import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Starts the application
 * @author Remi
 *
 */
public class start {
	public static Display f = new Display();
	public static int w = 1280;
	public static int h = 720;
	
	/**
	 * Shows a new window
	 * @param arg 
	 */
	public static void main(String[] arg) {
		
		/*
		 * To do:
		 * 	-Fit to screen
		 * 	-Insert shapes
		 * 	-Holding mouse instead of control (rmb removes)
		 * 	-Randomize
		 * 	-Stats
		 */		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		f.setSize(w, h);
		f.setResizable(true);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Game of Life");
		f.setLocationRelativeTo(null);

		//testing
		
		//new start();
		/*
		grid test = new grid(8,8);
		
		test.setTile(0, 2, true);
		test.setTile(1, 2, true);
		test.setTile(2, 2, true);
		test.setTile(2, 1, true);
		test.setTile(1, 0, true);
		System.out.println(test.toString());
		
		test.update();
		System.out.println(test.toString());
		*/
		
	}	
}
