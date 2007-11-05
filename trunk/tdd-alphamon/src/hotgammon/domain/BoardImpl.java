/**
 * 
 */
package hotgammon.domain;

import java.util.Iterator;

/**
 * @author jacob
 *
 */
public class BoardImpl implements Board {

	/* (non-Javadoc)
	 * @see hotgammon.domain.Board#getColor(hotgammon.domain.Location)
	 */
	public Color getColor(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see hotgammon.domain.Board#getCount(hotgammon.domain.Location)
	 */
	public int getCount(Location location) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see hotgammon.domain.Board#iterator()
	 */
	public Iterator<Location> iterator() {
		return new Iterator<Location>(){

			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			public Location next() {
				// TODO Auto-generated method stub
				return null;
			}

			public void remove() {
				// TODO Auto-generated method stub
				
			}};
	}

	/* (non-Javadoc)
	 * @see hotgammon.domain.Board#move(hotgammon.domain.Location, hotgammon.domain.Location)
	 */
	public void move(Location from, Location to) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see hotgammon.domain.Board#reset()
	 */
	public void reset() {
		// TODO Auto-generated method stub

	}

}
