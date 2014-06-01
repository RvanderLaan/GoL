import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;


public class help {
	
	private static final int width = 480;		//The width  of the help window
	private static final int height = 360;		//The height of the help window
	
	public static void show(Component parent) {
		JScrollPane help = new JScrollPane(createHelp());
		help.setPreferredSize(new Dimension(width, height));
		help.getVerticalScrollBar().setUnitIncrement(16);
		
		JScrollPane about = new JScrollPane(createAbout());
		about.setPreferredSize(new Dimension(width, height));
		about.getVerticalScrollBar().setUnitIncrement(16);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Help", help);
		tabbedPane.addTab("About", about);
		
		JOptionPane.showMessageDialog(parent, tabbedPane, "Help - Game Of Life", JOptionPane.PLAIN_MESSAGE);
	}
	
	private static JPanel createHelp(){
		JLabel GOL = new JLabel();
		
		JLabel UItitle = new JLabel("<HTML><b>User interface</b></HTML>");
		JLabel play = new JLabel();
		JLabel next = new JLabel();
		JLabel size = new JLabel();
		JLabel tilesize = new JLabel();
		JLabel insert = new JLabel();
		JLabel clear = new JLabel();
		JLabel fps = new JLabel();
		
		JLabel Interact = new JLabel();	
		
		String GOLtext = 	"<HTML><br/><b>User interface:</b>";
		GOLtext +=			"<p>The Game of Life is a simple simulator of life, it consists of tiles which are either alive or dead. ";
		GOLtext +=			"Every time a new generation is made, each tile interacts with its 8 neighbouring tiles by a few simple rules:";
		GOLtext +=			"<ol><li> Any live tile will die if it has less than 2 or more than 3 live neighbouring tiles.</li>";
		GOLtext +=			"<li> Any dead tile will come to life if it has exactly 3 live neighbouring tiles.</li></ol>";
		GOLtext +=			"If you wish to know more, I suggest reading the Wikipedia page of 'Conway's Game of Life'.</p></HTML>";
		GOL.setText(GOLtext);
		
		String interactTxt = "<HTML><b>Interacting</b>";
		interactTxt +=		"<p>You can click on tiles to bring them to life or to kill them. Holding control and hovering over tiles will have the same effect.</p>";
		Interact.setText(interactTxt);
		
		Dimension d = new Dimension(width-20, 130);
		GOL.setMaximumSize(d);
		GOL.setPreferredSize(d);
		GOL.setMinimumSize(d);
		
		Dimension d2 = new Dimension(width-20, 60);
		Interact.setMaximumSize(d2);
		Interact.setPreferredSize(d2);
		Interact.setMinimumSize(d2);
		
		try {
			play.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("auto.png" )), "Play"));
			next.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("next.png" )), "Next"));
			size.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("size.png" )), "New size"));
			tilesize.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("tilesize.png" )), "Tile size"));
			insert.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("insert.png" )), "Insert shape"));
			clear.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("clear.png" )), "Clear"));
			fps.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("pause.png" )), "Set FPS"));
		} catch (IOException e) {

		}
		play.setText("Toggle automatic updating of the tiles. The speed is set via the FPS slider.");
		next.setText("Update the tiles once.");
		size.setText("Set the size for a new grid.");
		tilesize.setText("Set the individual size of the tiles.");
		insert.setText("Insert a shape in the current grid.");
		clear.setText("Kill all tiles.");
		fps.setText("Set the speed of the automatic updating.");
		
		JPanel help = new JPanel();
		help.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		help.setLayout(new GridBagLayout());
		
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.insets = new Insets(2, 0, 2, 0);
		
		gc.anchor = GridBagConstraints.NORTHWEST;
		
		gc.gridy = 0;
		help.add(GOL, gc);
		gc.gridy = 1;
		help.add(Interact, gc);
		//gc.gridy = 2;
		gc.gridy = 3;
		help.add(UItitle, gc);
        gc.gridy = 4;
		help.add(play, gc);
        gc.gridy = 5;
		help.add(next, gc);
        gc.gridy = 6;
		help.add(size, gc);
		gc.gridy = 7;
		help.add(tilesize, gc);
		gc.gridy = 8;
		help.add(insert, gc);
        gc.gridy = 9;
		help.add(clear, gc);
        gc.gridy = 10;
		help.add(fps, gc);
		
		return help;
	}
	
	private static JPanel createAbout(){
		JLabel txt = new JLabel();
		String aboutTxt="<HTML><b>About:</b>";
		aboutTxt +=		"<p>This application was made by Remi 'ThaRemo' van der Laan in March 2014.</p>";
		aboutTxt +=		"Icons: <ul>";
		aboutTxt +=		"<li>Play, next & help - www.iconfinder.com</li>";
		aboutTxt +=		"<li>Others - Remi</li>";
		aboutTxt +=		"</ul></HTML>";
		
		txt.setText(aboutTxt);
		
		
		JPanel about = new JPanel();
		about.add(txt);
		
		Dimension d = new Dimension(width-20, height-250);
		txt.setMaximumSize(d);
		txt.setPreferredSize(d);
		txt.setMinimumSize(d);
		
		return about;
	}
 }
