package hotgammon.domain;

/** This class represents an enumeration of checker colours used in
 * Backgammon.
 *
 * Responsibility: to define the colours used in Backgammon
 *
 * We need to represent either the red or the black colour - but also
 * in some instances neither of the colours, for instance if we ask
 * what colour the checkers have on an empty location on the board.
 * This is represented by the NONE colour.
 *
 * For efficient data representation, colours can also be encoded as
 * integer values where negative values represent RED and positive
 * BLACK. Thus a location can store -5 to represent 5 red
 * checkers. Note also that the sign defines the way checkers move
 * given the integer encoding of Location instances.
 *
 * Author: Henrik Bærbak Christensen 
 */
public enum Color {
  RED(-1), NONE(0), BLACK(+1);

  private int sign;
  private Color(int sign) { this.sign = sign; }

  /** Return the numerical value that this colour is defined by
   * @return the numerical value that this colour is defined by
   */
  public final int getSign() {
    return sign;
  }

  /** Return the color that a numerical a value represents
   * @param value a numerical value that represents a color
   * @return the color the value represents
   */
  public static Color getColorFromNumerical(int value) {
    if (value < 0)
      return RED;
    if (value > 0)
      return BLACK;
    return NONE;
  }
  
  /** Return the string representation of this color.
   * @return a string representing the color, like "BLACK"
   */
  public String toString() {
    switch (this) {
    case RED :
      return "Red";
    case BLACK :
      return "Black";
    default :
      return "None";
    }
  }
}
