package hotgammon.domain;

import java.util.Iterator;

/** This class represents the concept of a backgammon game.
 * Responsibility:
 * A) handle game state turn taking: alternation between rolling the
 * dice and making moves
 * B) allow inspection of the board, dice, game state.
 * C) allow board moves and dice rolls
 * D) act as judge that may define actions as invalid
 *
 * Game is an example of the Facade pattern that encapsulate the
 * underlying structure of classes.
 *
 * Author: Henrik Bærbak Christensen 
*/

public interface Game {
  /** Reset the entire game to start from scratch.  No player is in
   * turn, and the game awaits a call to nextTurn to start a game.
   * @see nextTurn
   */
  public void newGame();

  /** Tell the game instance that the user wants the game to progress
   * after the 'turn has finished' i.e. the player in turn has
   * exhausted his/her abilities for moving checkers.  The game
   * responds by changing to the other player and rolling the dice. At
   * the beginning of the game (after 'newGame') the dice are rolled
   * and depending on the roll, the game progresses. NOTE that if the
   * dice rolled are equal in value (like 1,1) then the game state is
   * STILL in the initialisation phase (no player is in turn); thus
   * you must repeatedly invoke nextTurn() until the dice rolled are
   * not equal. The first non-equal valued dice rolled results in a
   * player being defined as the player to move first. If die 1 > die
   * 2 then red starts; otherwise it is black that moves first.
   * 
   * PRECONDITION: The player in turn has indeed exhausted the
   * his/her ability to make moves.
   */
  public void nextTurn();

  /** Move one checker from one location to another.
   * @param from the location to move the stone from
   * @param to the location to move the stone to
   * @return false if the indicated move is illegal 
   */
  public boolean move(Location from, Location to);

  /** Return the color of the player that is in turn i.e. is allowed
   * to make a move. 
   * @return the color of the player to move next or Color.NONE if the
   * turn is exhausted (that is, getNumberOfMovesLeft == 0).
   */
  public Color getPlayerInTurn();

  /** Return the number of moves left in the current turn. I.e. if a
   * new turn has just begun and the dice rolled are [1,2] the return
   * value is 2. If dice rolled are [3,3] then the return value is 4
   * (if using standard backgammon rules). The number of moves left
   * are subject to the backgammon rules, that is, if the player that
   * has just rolled cannot use the dice values at all (for instance
   * in situations where a checker is in the bar and the points are
   * blocked for the given rolled dice) then the return value is 0.
   * @return the number of moves left for the player-in-turn subject
   * to the rules of backgammon.
   */
  public int getNumberOfMovesLeft();

  /** Return an integer array of size exactly 2 containing the
    * values of the dice thrown.
    * @return array of two integers defining the dice values rolled
    * last.
    */
  public int[] diceThrown();

  /** Return an integer array of size in range [0;4] containing die
   * values that have not yet been used to move a checker.  Example:
   * If returned value is [3,3,3] then there are three moves left for
   * the player in turn, all of distance 3. If returned value is [2]
   * then only one move is left of distance 2.
   *
   * POSTCONDITION: The array is sorted so the largest die value is
   * first in the array.
   * @return int array of unused die values.
   */
  public int[] diceValuesLeft();

  /** Return the winner of this game.
   * @return the winner of the game; if game is still in progress, then
   * Color.NONE is returned.
   */
  public Color winner();

  /** Return an iterator that allows inspecting the board.
   * @return an iterator over the locations in the board.
  */
  public Iterator<Location> boardIterator();

  /** Get the colour of the checkers on a given location.
   * @param location the location on the board to check
   * @return the color of the checkers on this location
   */
  public Color getColor(Location location);

  /** Get the count of checkers of this location.
   * @param location the location to inspect
   * @return a integer value showing the number of checkers on this location.
   */
  public int getCount(Location location);
}
