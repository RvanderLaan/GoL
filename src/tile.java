
/**
 * A tile in the Game of Life is a entity that is either alive or dead.
 * 
 * @author Remi
 *
 */
public class tile {
	private boolean alive;
	
	/**
	 * This generates a tile entity, which is set to dead on default
	 */
	public tile() {
		alive = false;
	}
	
	/**
	 * Sets the status of the tile
	 * @param l True: Alive, False: Dead
	 */
	public void changeStatus(boolean l) {
		alive = l;
	}
	
	/**
	 * Returns wheter the tile is alive or dead.
	 * @return True: Alive, False: Dead
	 */
	public boolean getStatus() {
		return alive;
	}
	
	/**
	 * Kills the tile
	 */
	public void die() {
		alive = false;
	}
	
	/**
	 * Revives the tile
	 */
	public void live() {
		alive = true;
	}
}
