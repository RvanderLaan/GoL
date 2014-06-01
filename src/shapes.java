import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class shapes extends JPanel{
	private JScrollPane container;
	
	public void showShapes() {
		container = new JScrollPane();
		
	}
	
	public JPanel createShape(grid g) {
		JPanel shape = new JPanel();
		shape.setPreferredSize(new Dimension(100,100));
		
		
		return shape;
	}
}
