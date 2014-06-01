import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CellPane extends JPanel {

	private Color defaultBackground;
	
	private boolean status;
	private int tileSize;

	public CellPane(boolean stat, int tileS) {
		status = stat;
		tileSize = tileS;
		
		if (defaultBackground == null)
			defaultBackground = getBackground();
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if (e.isControlDown()) {
					toggle();
				}
				
				setBackground(getBackground().darker());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				update();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				toggle();
            }
			 
		});
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(tileSize, tileSize);
	}
	
	public void setSize(int size) {
		tileSize = size;
	}
	
	public void setStatus(boolean b) {
		status = b;
	}
	public boolean getStatus() {
		return status;
	}
	public void update() {
		if (status == true) {
			setBackground(Color.GREEN);
		}
		else {
			setBackground(defaultBackground);
		}
	}
	
	public void toggle() {
		if (status == false) {
			setBackground(Color.GREEN);
			status = true;
		}
		else {
			setBackground(defaultBackground);
			status = false;
		}
	}
	
	public void setTxt(String txt) {
		this.add(new JLabel(txt));
	}
}
