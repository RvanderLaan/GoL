import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;


public class gamePane extends JPanel {

	private int height;
	private int width;
	private int tilesize;
	private grid grid;
	CellPane[][] cellPanes;

	/**
	 * Creates a gamePane containing CellPanes
	 * @param x The amount of horizontal CellPanes
	 * @param y The amount of vertical CellPanes
	 */
	public gamePane(int x, int y, int tileS) {
		width = x;
		height = y;
		tilesize = tileS;
		
		grid = new grid(width, height);
		
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		
		cellPanes = new CellPane[height][width];
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				gbc.gridx = col;
				gbc.gridy = row;

				cellPanes[row][col] = new CellPane(grid.getTile(col, row), tilesize);
				
				Border border = null;
				if (row < height - 1) {
					if (col < width - 1) {
						border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
					}
				} else {
					if (col < width -1) {
						border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
					} else {
						border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
					}
				}
				cellPanes[row][col].setBorder(border);
				add(cellPanes[row][col], gbc);
			}
		}
	}
	
	public int getTileSize() {
		return tilesize;
	}
	
	public void setTileSize(int size) {	
		tilesize = size;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				cellPanes[row][col].setSize(size);
			}
		}
	}
	
	public void reset() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				cellPanes[row][col].setStatus(false);
				
				grid.setTile(col, row, false);
				cellPanes[row][col].update();
				
			}
		}
	}
	
	public void update() {
		//Store values in grid
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				grid.setTile(col, row, cellPanes[row][col].getStatus());
			}
		}
		
		//Update the grid
		grid.update();
		
		//Set values in GUI
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				cellPanes[row][col].setStatus(grid.getTile(col, row));
				cellPanes[row][col].update();
			}
		}
	}
	
	public void randomize() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (Math.random() >= 0.5) {
					grid.setTile(col, row, true);
					
				}
				else {
					grid.setTile(col, row, false);
				}
				cellPanes[row][col].setStatus(grid.getTile(col, row));
				cellPanes[row][col].update();
			}
		}
	}
}
