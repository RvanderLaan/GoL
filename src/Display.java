import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Creates the main window which contains a JToolBar and a JPanel which contains more JPanels which represent tiles
 * @author Remi
 *
 */
public class Display extends JFrame implements ActionListener, ChangeListener {

	private GridBagConstraints gridbag;
	private boolean firstauto;
	private boolean auto;
	
	private boolean slider0;
	private int fps;
	private ScheduledExecutorService scheduler;
	private ScheduledFuture<?> handle;

	public menu menubalk;
	public JScrollPane menupanel;
	public gamePane gamepanel;
	public JScrollPane gameScrollPane;

	/**
	 * Sets up the window
	 */
	public Display() {
		//Set minimum size
		Dimension d = new Dimension(640, 360);
		this.setMinimumSize(d);
		
		//Set default values
		slider0 = false;
		firstauto = false;
		auto = false;
		fps = 500;
		
		//Set layout
		this.setLayout(new GridBagLayout());
		gridbag = new GridBagConstraints();
		
		gridbag.gridx = 0;
		gridbag.gridy = 2;

		//Add components
		addMenu();
		addGamePane(32,32, 20);
		
		//Add custom icon
		try {
			this.setIconImage(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("insert.png"))).getImage());
		} catch (NullPointerException | IOException e) {
		}
	}

	/**
	 * Adds a toolbar to the window
	 */
	public void addMenu() {
		gridbag.gridx = 0;
		gridbag.gridy = 0;
		gridbag.fill = GridBagConstraints.HORIZONTAL;
		gridbag.weightx = 0;
		gridbag.weighty = 0;
		
		menubalk = new menu(this, this);

		this.add(menubalk, gridbag);
	}

	/**
	 * Adds the game panel to the window
	 * @param x
	 * @param y
	 */
	public void addGamePane(int x, int y, int s) {
		gridbag.gridx = 0;
		gridbag.gridy = 1;
		gridbag.fill = GridBagConstraints.BOTH;
		gridbag.weightx = 1;
		gridbag.weighty = 1;

		gamepanel = new gamePane(x, y, s);

		gameScrollPane = new JScrollPane(gamepanel);
		
		gameScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		gameScrollPane.getHorizontalScrollBar().setUnitIncrement(16);

		this.add(gameScrollPane, gridbag);
	}

	/**
	 * Handles the mouse events 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ("Next"):
			gamepanel.update();
			break;
		case ("Clear"):
			gamepanel.reset();
			break;
		case ("Auto"):
			if (auto) 
				auto = false;
			else 
				auto = true;
			auto();
			break;
		case ("New size"):
			setSize();
			break;
		case ("Help"):
			help.show(this);
			break;
		case ("Tile size"):
			setTileSize();
			break;
		case ("Fit to screen"):
			gamepanel.randomize();
			this.revalidate();
			//fit();
			break;
		}
	}
	
	private int decrement(int number, int mod) {
		while (number % mod != 1) {
			number--;
		}
		return number/mod;
	}

	public void fit() {
		int newWidth = (int) decrement(gamepanel.getHeight(), 20);
		int newHeight = (int) decrement(gamepanel.getWidth(), 20);
		//int newTileSize;
		
		this.remove(gameScrollPane);

		addGamePane(newHeight, newWidth, 20);
		
		this.revalidate();
	}
	/**
	 * Automatically updates the game pane at a set time
	 */
	public void auto() {		
		//Start auto
		if (firstauto == false) {
			firstauto = true;
			
			scheduler = Executors.newScheduledThreadPool(1);
			Runnable toRun = new Runnable() {
				public void run() {
					if (auto == true && slider0 == false)
						gamepanel.update();
				}
			};
			
			handle = scheduler.scheduleAtFixedRate(toRun, fps, fps, TimeUnit.MILLISECONDS);
		}
	}
	
	public void setSize() {
		//Get input
		String newSize = JOptionPane.showInputDialog(this, "Set a new size between 2 and 150:\nWarning: High sizes require much memory and slow down the application.", "32");
		
		try {
			//If the window is closed it should not show an error message
			if (newSize != null) {
				int newSizeInt = Integer.parseInt(newSize);
				if (newSizeInt < 2 || newSizeInt > 150) {
					JOptionPane.showMessageDialog(this,
						    "Your input was not between 2 and 150.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					int tilesize = gamepanel.getTileSize();
					this.remove(gameScrollPane);
					
					addGamePane(newSizeInt, newSizeInt, tilesize);
					//gamepanel.update();
					this.revalidate();
				}
			}
		}
		catch(NumberFormatException e) { 
			JOptionPane.showMessageDialog(this,
		    "Your input was not a valid number.",
		    "Error",
		    JOptionPane.ERROR_MESSAGE);
	    }
		
	}
	
	public void setTileSize() {
		//Get input
		String newSize = JOptionPane.showInputDialog(this, "Set a new tile size between 5 and 50:", "20");
		try {
			//If the window is closed it should not show an error message
			if (newSize != null) {
				int newSizeInt = Integer.parseInt(newSize);
				if (newSizeInt < 5 || newSizeInt > 50) {
					JOptionPane.showMessageDialog(this,
						    "Your input was not between 5 and 50.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					gamepanel.setTileSize(newSizeInt);
					gamepanel.revalidate();
				}
			}
		}
		catch(NumberFormatException e) { 
			JOptionPane.showMessageDialog(this,
		    "Your input was not a valid number.",
		    "Error",
		    JOptionPane.ERROR_MESSAGE);
	    }
	}

	@Override
	//Handles sliders
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
	        int input = (int)source.getValue();
	        if (input == 0)
	        	slider0 = true;
	        else {
	        	//Automatic is on if input != 0
	        	slider0 = false;
	        	//Calculate fps in MS
	        	fps = (int) Math.floor((1 / (double) input) * 1000);
	        	//Cancel the current scheduler
	        	if (handle != null) 
	        		handle.cancel(false);
	        	
	        	//Reset the first auto
	        	firstauto = false;
	        	//Create new auto
	        	auto();
	        	
	        }
	    }
		
	}
}
