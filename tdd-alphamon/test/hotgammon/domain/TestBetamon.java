package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for Betamon game.
 * 
 * 1. A checker may only move in the direction of the player�’s own inner table.
 * 2. The distance travelled must equal the value of a rolled die, and a die
 * value must only be �”used�” once. That is, roll [2,3] allows one checker to
 * move 2 and one checker to move 3: it should not be allowed to move a checker,
 * say 3 and then 3 again. 3. A player moving a checker to a location where the
 * opponent has ex- actly one checker will lead to the checker moving to the
 * bar. Example: Black has a single checker at R8 and black moves a checker
 * there. Af- ter this move, there must be one black checker at R8 and one
 * (more) red checker at R BAR. 4. A location with 2 or more opponent checkers
 * is a blocked point and you are not allowed to move there. 5. A checker in the
 * bar must move to the opponent�’s inner table. For instance if Black has a
 * checker in the bar and rolls [1,4] then he must see if he can move a checker
 * to either R1 or R4. 6. Moves on the board are not allowed until the player
 * has no more checkers left in his/her bar. 7. A checker can always be born off
 * (you do not have to wait until all your checkers are in the inner table as in
 * backgammon). To bear off the distance must match a die value exactly. For
 * example for black to bear off B4 a die 4 must have been rolled and not used
 * yet. All other aspects of Betamon are like Alphamon: the dice are
 * predictable, red wins after six turns, etc.
 * 
 * 
 */

public class TestBetamon {

    private Game game;

    @Before
    public void setup() {
        game = new StandardGame(new BetamonFactory());
        game.newGame();
        game.nextTurn();
    }

    /**
     * the dice rolls are not random. The first two dice rolls give [1,2], the
     * next two [3,4], next [5,6], and then it starts all over again.
     */
    @Test
    public void testDiceThrown() {
        testDie(game.diceThrown(), 1, 2);
        game.nextTurn();
        testDie(game.diceThrown(), 1, 2);
        game.nextTurn();
        testDie(game.diceThrown(), 3, 4);
        game.nextTurn();
        testDie(game.diceThrown(), 3, 4);
        game.nextTurn();
        testDie(game.diceThrown(), 5, 6);
        game.nextTurn();
        testDie(game.diceThrown(), 1, 2);
        game.nextTurn();

    }

    /**
     * nextTurn() -> ny spiller
     */
    @Test
    public void testNextPlayer() {

        assertEquals(Color.BLACK, game.getPlayerInTurn());
        game.nextTurn();
        assertEquals(Color.RED, game.getPlayerInTurn());
        game.nextTurn();
        assertEquals(Color.BLACK, game.getPlayerInTurn());
    }

    private void testDie(int[] d, int a, int b) {
        assertEquals(a, d[0]);
        assertEquals(b, d[1]);
    }

     @Test
    public void numberOfMovesLeftBeforeMove() {
        assertEquals(2, game.getNumberOfMovesLeft());
    }

    /**
     * black vil always start in Betamon
     */
    @Test
    public void onlyStartPlayerMoves() {
        assertTrue(game.move(Location.R1, Location.R3));
        assertEquals(1, game.getCount(Location.R3));
        assertEquals(1, game.getCount(Location.R1));
    }

    @Test
    public void otherPlayerCantMove() {
        assertFalse(game.move(Location.R6, Location.R3));
        assertEquals(2, game.getCount(Location.R1));
    }

    /*
     * 4. A location with 2 or more opponent checkers is a blocked point and you
     * are not allowed to move there.
     */
    @Test
    public void moreThanOneCheckerOnToLocation() {

        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        game.nextTurn();
        // to der gerne skulle vaere lovlige og stemme med terningerne
        assertTrue(game.move(Location.R6, Location.R5));
        assertTrue(game.move(Location.R8, Location.R6));
        game.nextTurn();
        assertTrue(game.move(Location.R2, Location.R5));
        assertTrue(game.move(Location.R3, Location.R7));
        game.nextTurn();
        // der staar nu to sorte paa R4
        assertFalse(game.move(Location.R6, Location.R3));
    }

    @Test
    public void numberOfMovesLeftAfterMove() {
        game.move(Location.R1, Location.R3);
        assertEquals(1, game.getNumberOfMovesLeft());
        game.move(Location.R1, Location.R2);
        assertEquals(0, game.getNumberOfMovesLeft());
    }

    @Test
    public void getPlayerInTurnWithNoMoves() {
        assertTrue(game.move(Location.R1, Location.R3));
        assertTrue(game.move(Location.R1, Location.R2));
        assertEquals(Color.NONE, game.getPlayerInTurn());
    }

    @Test
    public void winner() {
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertEquals(Color.RED, game.winner());
    }

    @Test
    public void moveWhenNoMoreMoves() {
        assertTrue(game.move(Location.R1, Location.R3));
        assertTrue(game.move(Location.R1, Location.R2));
        assertFalse(game.move(Location.R3, Location.R4));
    }

    @Test
    public void onlyCorrectDirection() {
        // et forkert
        assertFalse(game.move(Location.B6, Location.B7));
        // to der gerne skulle v;re lovlige og stemme med terningerne
        assertTrue(game.move(Location.B6, Location.B5));
        assertTrue(game.move(Location.B6, Location.B4));
        game.nextTurn();
        assertFalse(game.move(Location.R6, Location.R7));
        // to der gerne skulle v;re lovlige og stemme med terningerne
        assertTrue(game.move(Location.R6, Location.R5));
        assertTrue(game.move(Location.R6, Location.R4));

    }

    @Test
    public void diceValuesLeft() {
        assertEquals(Helpers.atos(new int[] { 2, 1 }),
                Helpers.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.R1, Location.R3));
        assertEquals(Helpers.atos(new int[] { 1 }),
                Helpers.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.R1, Location.R2));
        assertEquals("", Helpers.atos(game.diceValuesLeft()));

        game.nextTurn();

        assertEquals(Helpers.atos(new int[] { 2, 1 }),
                Helpers.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.B1, Location.B2));
        assertEquals(Helpers.atos(new int[] { 2 }),
                Helpers.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.B1, Location.B3));
        assertEquals("", Helpers.atos(game.diceValuesLeft()));
    }

    /**
     * der er en p�Á� et felt move R1 R3 move R1 R4 nextTurn() move R6 R3 ->
     * true og black bar indeholder een brik, blot
     */
    @Test
    public void onlyOneChekerAtMoveToLocation() {
        game.move(Location.R1, Location.R3);
        game.move(Location.R3, Location.R4);
        game.nextTurn();
        assertTrue(game.move(Location.R6, Location.R4));
        assertEquals(1, game.getCount(Location.B_BAR));
        assertEquals(Color.RED, game.getColor(Location.R4));
    }

    @Test
    public void bearOffTest() {
        assertTrue(game.move(Location.B6, Location.B4));
        assertTrue(game.move(Location.B4, Location.B3));
        game.nextTurn();
        assertTrue(game.move(Location.R6, Location.R4));
        assertTrue(game.move(Location.R4, Location.R3));
        game.nextTurn();
        assertTrue(game.move(Location.B3, Location.B_BEAR_OFF));
        // assertTrue(game.move(Location.B2, Location.B_BEAR_OFF));
        game.nextTurn();
        // assertTrue(game.move(Location.R3, Location.R2));
        assertTrue(game.move(Location.R3, Location.R_BEAR_OFF));
    }

    @Test
    public void blackOnBarGettingBack() {
        // onlyOneChekerAtMoveToLocation(); // laver de samme moves r'd har een
        // ener tilbage

        game.move(Location.R1, Location.R3);
        game.move(Location.R3, Location.R4);
        game.nextTurn();
        assertTrue(game.move(Location.R6, Location.R4));
        assertEquals(1, game.getCount(Location.B_BAR));
        assertEquals(Color.RED, game.getColor(Location.R4));

        assertTrue(game.move(Location.R6, Location.R5));
        game.nextTurn();
        assertFalse(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.B_BAR, Location.R3));
        assertTrue(game.move(Location.R1, Location.R5));
    }

    @Test
    public void sequence1() {
        game = new StandardGame(new BetamonFactory());
        game.newGame();
        game.nextTurn(); // roll dice
        assertTrue(game.move(Location.R1, Location.R3));
    }

    @Test
    public void diceMatchMove() {
        assertEquals(Helpers.atos(new int[] { 2, 1 }),
                Helpers.atos(game.diceValuesLeft()));
        assertFalse(game.move(Location.R1, Location.R4));
        assertEquals(Helpers.atos(new int[] { 2, 1 }),
                Helpers.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.R1, Location.R2));
        /*
         * assertEquals("", Helpers.atos(game.diceValuesLeft()));
         * 
         * game.nextTurn();
         * 
         * assertEquals(Helpers.atos(new int[] { 2, 1 }), Helpers.atos(game
         * .diceValuesLeft())); assertTrue(game.move(Location.B1, Location.B2));
         * assertEquals(Helpers.atos(new int[] { 2 }), Helpers.atos(game
         * .diceValuesLeft())); assertTrue(game.move(Location.B1, Location.B3));
         * assertEquals("", Helpers.atos(game.diceValuesLeft()));
         */
    }

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestBetamon.class);
    }
}
