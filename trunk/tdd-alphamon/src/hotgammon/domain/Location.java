package hotgammon.domain;

/** This class represents an enumeration of board locations on the Backgammon
 * board.
 *
 * Responsibility: A) define all locations available on a backgammon
 * board B) provide an alternative integer representation for easy
 * distance calculation
 *
 * Note: Locations are named B1-B12 for the locations on black's side
 * of the board, and R1-R12 for red's side. The special locations
 * "bar" and "bear off" are split: B_BAR is the bar for black checkers
 * only and B_BEAR_OFF is the location where black checkers are stored
 * when born off the board. The same goes for R_BAR and R_BEAR_OFF.
 * 
 * The location can return an integer encoding for easy distance
 * calculations and potentially also easy mapping into an array. B1 =
 * 24, B2 = 23, ..., B12 = 13, R12 = 12, ..., R1 = 1. Thus the
 * signed distance between two locations can be calculated by
 * a.getIndex() - b.getIndex(). 
 *
 * The bars are just outside this range: B_BAR = 0 (thus a black
 * checker at B_BAR moving 2 will end at index 2 that correctly equals
 * R2) and R_BAR = 25.
 *
 * Author: Henrik Bærbak Christensen 
*/
public enum Location {

  // enumerate all ordinary board locations
  B1("B1", 24),
  B2("B2", 23),
  B3("B3", 22),
  B4("B4", 21),
  B5("B5", 20),
  B6("B6", 19),
  B7("B7", 18),
  B8("B8", 17),
  B9("B9", 16),
  B10("B10", 15),
  B11("B11", 14),
  B12("B12", 13),
  
  R1("R1", 1),
  R2("R2", 2),
  R3("R3", 3),
  R4("R4", 4),
  R5("R5", 5),
  R6("R6", 6),
  R7("R7", 7),
  R8("R8", 8),
  R9("R9", 9),
  R10("R10", 10),
  R11("R11", 11),
  R12("R12", 12),
  
  // the bars (gærde)
  B_BAR("B_BAR", 0),
  R_BAR("R_BAR", 25),
  
  // the location of checkers that have been born off
  B_BEAR_OFF("B_BEAR_OFF", 26),
  R_BEAR_OFF("R_BEAR_OFF", 27); 
  
  private final String name;
  private final int index;
  /** Private constructor disallows any other instances than those
   *  defined by the Location class itself.
   */
  private Location(String locationName, int index) {
    name = locationName; this.index = index;
  }

  /** Return the integer encoding of this location for distance
   * calculations etc.
   * @return the index of this location.
   */
  public int getIndex() {
    return index;
  }

  /** Return the signed distance between any two locations, from and
   * to. Signed means negative values are returned if the to-from is
   * in red's move direction; and positive if it is in blacks. Note
   * that distances to the bear off is properly calculated. That is,
   * the distance B3 to B_BEAR_OFF is 3 and R3 to R_BEAR_OFF = -3.
   * @param from the location a checker is moving from
   * @param to the location a checker is moving to
   * @return the signed distance between the locations.
   */
  public static int distance(Location from, Location to) {
    // Locations are numbered from R1 = 1 to B1 = 24. Thus red player
    // must move such  that to - from < 0 and black such that to -
    // from > 0. However, bear off is special!
    int distance;
    if ( to == Location.B_BEAR_OFF ) {
      distance = 25 - from.getIndex();
    } else if (  to == Location.R_BEAR_OFF ) {
      distance = 0 - from.getIndex();
    } else {
      distance = to.getIndex() - from.getIndex();
    }
    return distance;
  }



  /** Return the name of this location.
   * @return the name of this location.
   */
  public String toString() { return name; }
}
