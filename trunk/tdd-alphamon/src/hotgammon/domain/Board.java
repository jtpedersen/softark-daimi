package hotgammon.domain;

import java.util.Iterator;

/** This interface defines the contract of the backgammon board.
 * Responsibility: 
 *
 * A) to act as a structured collection holding the
 * checkers in the game 
 *
 * B) to facilitate movement of checkers in the collection 
 *
 * C) allow iteration over its contents
 *
 * It is explicitly NOT the responsibility of the board to do any kind
 * of validation of moves on the board.
 *
 * A board implementation must keep the following class invariant:
 * There are always 15 RED and 15 BLACK checkers (checkers born off
 * must be on the BEAR_OFF locations.)
 *
 * Author: Henrik Bærbak Christensen 
*/

public interface Board extends Iterable<Location> {

  /** Resets all board checkers to their initial position */
  public void reset();
	
  /** Move one checker from one location to another.
   * PRECONDITION: The move must be valid on the present board!
   * @param from the location to move the checker from
   * @param to the location to move the checker to
  */
  public void move(Location from, Location to);

  /** Get the colour of the checkers on a given location.
   *  
   * @param location the location on the board to check
   * @return the color of the checkers on this location
   */
  public Color getColor(Location location);

  /** Get the count of checkers of this location.
   * @param location the location to inspect
   * @return a integer value showing the number of checkers on this location.
   */
  public int getCount(Location location);
  
  /** Return an interator that will enumerate all Locations on the board.
   * Each location is simply the location enumeration object, thus you must
   * access the location's contents through the board interface.
   * Example:
   * 
   * for (Location l: board) {
   *  noOfCheckers += board.getCount(l);
   * }
   * 
   * @return an iterator over locations on the board
   */
  public Iterator<Location> iterator();
}
