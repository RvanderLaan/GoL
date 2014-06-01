/**
 * A grid is a 2D plane of tile entities, which are either dead or alive.
 * @author Remi
 *
 */
public class grid {
	private tile[][] tiles;
	public grid bak;

	/**
	 * Creates a grid of w by h tiles
	 * @param w The width of the grid
	 * @param h The height of the grid
	 */
	public grid(int w, int h) {
		// Create all the tiles
		if (w > 0 && h > 0) {
			tiles = new tile[h][w];
			for (int i = 0; i < this.height(); i++) {
				for (int j = 0; j < this.width(); j++) {
					tiles[j][i] = new tile();
				}
			}
		}
	}

	/**
	 * Returns the height of the grid
	 * @return The height of the grid
	 */
	public int height() {
		if (tiles != null)
			return tiles.length;
		return 0;
	}

	/**
	 * Returns the width of the grid
	 * @return The width of the grid
	 */
	public int width() {
		if (tiles != null)
			return tiles[0].length;
		return 0;
	}

	/**
	 * Sets the status of a tile
	 * @param x The horizontal position of the tile
	 * @param y The vertical position of the tile
	 * @param l True: Alive, False: Dead
	 */
	public void setTile(int x, int y, boolean l) {
		tiles[y][x].changeStatus(l);
	}

	/**
	 * Returns the status of a tile
	 * @param x The horizontal position of the tile
	 * @param y The vertical position of the tile
	 * @return True: Alive, False: Dead
	 */
	public boolean getTile(int x, int y) {
		return tiles[y][x].getStatus();
	}

	/**
	 * Updates the grid according to two rules:
	 * 1) If a tile is alive, it will only survive if there are either 2 or 3 alive neighboring tiles
	 * 2) If a tile is dead, it will come alive if there are exactly 3 alive neighboring tiles
	 */
	public void update() {
		// At the first gen, there is no backup
			bak = this.copy();

		// Check every tile
		for (int i = 0; i < this.height(); i++) {
			for (int j = 0; j < this.width(); j++) {
				// Get the amount of surrounding tiles that are alive
				int sur = bak.surrounding(i, j);

				// If the tile is alive:
				if (bak.getTile(i, j)) {
					if (sur < 2 || sur > 3) {
						tiles[j][i].die();
					}
				}

				// If the tile is dead:
				else {
					if (sur == 3) {
						tiles[j][i].live();
					}
				}
			}
		}
		//bak = this.copy();
	}
	
	public void randomize() {
		for (int i = 0; i < this.height(); i++) {
			for (int j = 0; j < this.width(); j++) {
				if (Math.random() >= 0.5)
					tiles[j][i].live();
				else
					tiles[j][i].die();
			}
		}
	}

	/**
	 * Returns the amount of living neighboring tiles around a specified tile
	 * @param x The horizontal position of the tile
	 * @param y The vertical position of the tile
	 * @return The amount of living neighboring the tile at x,y
	 */
	public int surrounding(int x, int y) {
		int count = 0;

		// Create a 3x3 grid around (x,y)
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {

				// If it isn't the current tile
				if (j != 0 || i != 0) {
					int x2 = x + j;
					int y2 = y + i;

					// If the tile exists
					if (y2 >= 0 && y2 < this.height() && x2 >= 0
							&& x2 < this.width()) {

						// If it's alive
						if (this.getTile(x2, y2)) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	/**
	 * Returns a string representation of a grid
	 * @return The string representation of a grid
	 */
	public String toString() {
		// At the first gen, there is no backup
		if (bak == null)
			bak = this.copy();

		String output = "";
		for (int i = 0; i < this.height(); i++) {
			for (int j = 0; j < this.width(); j++) {
				if (tiles[i][j].getStatus())
					output += surrounding(j, i);
				else
					output += "-";
				output += " ";
				if (j == this.width() - 1) {
					output += "\n";
				}
			}
			if (i == this.height()) {
				output += "\n";
			}
		}
		return output;
	}

	/**
	 * Copies all values of the tiles from this grid to a copy
	 * @return A new grid with the same tiles as the current one
	 */
	public grid copy() {
		grid output = new grid(this.width(), this.height());

		for (int i = 0; i < this.height(); i++) {
			for (int j = 0; j < this.width(); j++) {
				output.setTile(i, j, this.getTile(i, j));
			}
		}

		return output;
	}
}
