import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

public class menu extends JToolBar {
	private ActionListener actionListener;
	private ChangeListener changeListener;

	
	public menu(ActionListener aL, ChangeListener cL) {
		super(JToolBar.HORIZONTAL);
		
		this.setFloatable(false);
		this.setVisible(true);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		actionListener = aL;
		changeListener = cL;
		
		//Set up fps slider
		JLabel fpstit = new JLabel("FPS", JLabel.CENTER);
		JSlider fps = new JSlider(JSlider.HORIZONTAL, 0, 60, 5);
		
		Dimension d = fps.getPreferredSize();
		d.width = 300;
		d.height = 40;
		fps.setMinimumSize(d);
		fps.setMaximumSize(d);
		
		fps.setMajorTickSpacing(10);
		fps.setMinorTickSpacing(5);
		fps.setPaintTicks(true);
		fps.setPaintLabels(true);
		
		fps.addChangeListener(cL);
		
		//Set up play button
		JToggleButton play = new JToggleButton();
		play.setToolTipText("Play/pause");
		play.setFocusPainted(false);
		
		play.setActionCommand("Auto");
		play.addActionListener(actionListener);
		
		try { //If it works, set the icon to the specified icon
			play.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("auto.png" )), "Play/pause"));
			play.setSelectedIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("pause.png" )), "Play/pause"));
		} catch (IOException e) {
			//Otherwise let the button display the specified alt-text
			play.setText("Play/pause");
		}
		

		//Add components in GUI
		//this.add(addButton("Previous", "prev"));
		this.add(play);
		this.add(addButton("Next", "next"));
		
		addSeparator();
		
		this.add(addButton("New size", "size"));
		this.add(addButton("Tile size", "tilesize"));
		this.add(addButton("Insert shape", "insert"));
		this.add(addButton("Clear", "clear"));
		
		addSeparator();
		
		this.add(fpstit);
		this.add(fps);
		
		addSeparator();
		
		this.add(addButton("Help","help"));
		
		this.add(addButton("Fit to screen", "size"));
	}
	
	private JButton addButton(String name, String path) {
		JButton butt = new JButton();
		//Sets the hovertext to the specified text
		butt.setToolTipText(name);
				
		//Try to set assign the icon to the button	
		try { //If it works, set the icon to the specified icon
			butt.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(path + ".png" )), name));
		} catch (IOException e) {
			//Otherwise let the button display the specified alt-text
			butt.setText(name);
		}

		butt.setFocusPainted(false);
		butt.setActionCommand(name);
		butt.addActionListener(actionListener);
		
		return butt;
	}
}
