package hotgammon.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Arrays;

public class AlphamonGame implements Game {

    private int turn;
    private Color currentPlayer;
    private Color winner;
    private Board board;
    private int numberOfMovesLeft;
    private int[] diceThrown;
    private ArrayList<Integer> movesLeft;

    /**
     * Reset the entire game to start from scratch. No player is in turn, and
     * the game awaits a call to nextTurn to start a game.
     * 
     * @see nextTurn
     */
    public void newGame() {
        board = new StandardBoard();
        currentPlayer = Color.NONE;
        turn = 0;
        diceThrown = null;
        movesLeft = null;
        numberOfMovesLeft = 0;
        winner = Color.NONE;
    }

    /**
     * Tell the game instance that the user wants the game to progress after the
     * 'turn has finished' i.e. the player in turn has exhausted his/her
     * abilities for moving checkers. The game responds by changing to the other
     * player and rolling the dice. At the beginning of the game (after
     * 'newGame') the dice are rolled and depending on the roll, the game
     * progresses. NOTE that if the dice rolled are equal in value (like 1,1)
     * then the game state is STILL in the initialisation phase (no player is in
     * turn); thus you must repeatedly invoke nextTurn() until the dice rolled
     * are not equal. The first non-equal valued dice rolled results in a player
     * being defined as the player to move first. If die 1 > die 2 then red
     * starts; otherwise it is black that moves first.
     * 
     * PRECONDITION: The player in turn has indeed exhausted the his/her ability
     * to make moves.
     */
    public void nextTurn() {
        if (currentPlayer == null 
            || currentPlayer == Color.NONE 
            || currentPlayer == Color.RED)
            currentPlayer = Color.BLACK;
        else 
            currentPlayer = Color.RED;
        numberOfMovesLeft = 2; // there is alwas to moves
        this.throwDice();
        turn++;
        if (turn >= 6) {
            winner = Color.RED;
        }
    }

    /**
     * Move one checker from one location to another.
     * 
     * @param from
     *            the location to move the stone from
     * @param to
     *            the location to move the stone to
     * @return false if the indicated move is illegal
     */
    public boolean move(Location from, Location to) {
        if (this.getNumberOfMovesLeft() == 0)
            return false;
        if (board.getCount(from) == 0)
            return false;
        /* This only applys for spikes.
           if (board.getCount(to) >= 5)
           return false;
        */
        if (board.getColor(from) != currentPlayer)
            return false;
        if (board.getColor(to) != Color.NONE
            && board.getColor(to) != currentPlayer)
            return false;
        
        board.move(from, to);
        numberOfMovesLeft--;
        removeDiceValue( Location.distance(from, to) );

        return true;
    }

    /**
     * Return the color of the player that is in turn i.e. is allowed to make a
     * move.
     * 
     * @return the color of the player to move next or Color.NONE if the turn is
     *         exhausted (that is, getNumberOfMovesLeft == 0).
     */
    public Color getPlayerInTurn() {
        return this.getNumberOfMovesLeft() == 0 ? Color.NONE : currentPlayer;
    }

    /**
     * Return the number of moves left in the current turn. I.e. if a new turn
     * has just begun and the dice rolled are [1,2] the return value is 2. If
     * dice rolled are [3,3] then the return value is 4 (if using standard
     * backgammon rules). The number of moves left are subject to the backgammon
     * rules, that is, if the player that has just rolled cannot use the dice
     * values at all (for instance in situations where a checker is in the bar
     * and the points are blocked for the given rolled dice) then the return
     * value is 0.
     * 
     * @return the number of moves left for the player-in-turn subject to the
     *         rules of backgammon.
     */
    public int getNumberOfMovesLeft() {
        return numberOfMovesLeft;
    }

    private void throwDice() {
        switch (turn%5) {
        case 0:
        case 1:
            diceThrown = new int[] { 1, 2 }; 
            break;
        case 2:
        case 3:
            diceThrown = new int[] { 3, 4 };
            break;
        case 4:
            diceThrown = new int[] { 5, 6 };
            break;
        }

        movesLeft = new ArrayList<Integer>();
        for(Integer i: diceThrown)
            movesLeft.add(i);
    }
    public void removeDiceValue(int die) {
        die = Math.abs(die);

        for(int i = 0; i<movesLeft.size(); i++) {
            if (movesLeft.get(i) == die) {
                movesLeft.remove(i);
            }
        }
 
    }

    /**
     * Return an integer array of size exactly 2 containing the values of the
     * dice thrown.
     * 
     * @return array of two integers defining the dice values rolled last.
     */
    public int[] diceThrown() {
        return diceThrown;
    }

    /**
     * Return an integer array of size in range [0;4] containing die values that
     * have not yet been used to move a checker. Example: If returned value is
     * [3,3,3] then there are three moves left for the player in turn, all of
     * distance 3. If returned value is [2] then only one move is left of
     * distance 2.
     * 
     * POSTCONDITION: The array is sorted so the largest die value is first in
     * the array.
     * 
     * @return int array of unused die values.
     */
    public int[] diceValuesLeft() {
        Collections.sort(movesLeft);
        Collections.reverse(movesLeft);
        int[] tmp = new int[movesLeft.size()];  int i= 0;
        for(Integer j: movesLeft)
            tmp[i++] =j;
        return tmp;
    }

    /**
     * Return the winner of this game.
     * 
     * @return the winner of the game; if game is still in progress, then
     *         Color.NONE is returned.
     */
    public Color winner() {
        return winner;
    }

    /**
     * Return an iterator that allows inspecting the board.
     * 
     * @return an iterator over the locations in the board.
     */
    public Iterator<Location> boardIterator() {
        return board.iterator();
    }

    /**
     * Get the colour of the checkers on a given location.
     * 
     * @param location
     *            the location on the board to check
     * @return the color of the checkers on this location
     */
    public Color getColor(Location location) {
        return board.getColor(location);
    }

    /**
     * Get the count of checkers of this location.
     * 
     * @param location
     *            the location to inspect
     * @return a integer value showing the number of checkers on this location.
     */
    public int getCount(Location location) {
        return board.getCount(location);
    }
}
