package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for Betamon game.
 * 
 *    1. A checker may only move in the direction of the player’s own inner
      table.
   2. The distance travelled must equal the value of a rolled die, and a die
      value must only be ”used” once. That is, roll [2,3] allows one checker
      to move 2 and one checker to move 3: it should not be allowed to
      move a checker, say 3 and then 3 again.
   3. A player moving a checker to a location where the opponent has ex-
      actly one checker will lead to the checker moving to the bar. Example:
      Black has a single checker at R8 and black moves a checker there. Af-
      ter this move, there must be one black checker at R8 and one (more)
      red checker at R BAR.
   4. A location with 2 or more opponent checkers is a blocked point and
      you are not allowed to move there.
   5. A checker in the bar must move to the opponent’s inner table. For
      instance if Black has a checker in the bar and rolls [1,4] then he must
      see if he can move a checker to either R1 or R4.
   6. Moves on the board are not allowed until the player has no more
      checkers left in his/her bar.
   7. A checker can always be born off (you do not have to wait until all
      your checkers are in the inner table as in backgammon). To bear off
      the distance must match a die value exactly. For example for black to
      bear off B4 a die 4 must have been rolled and not used yet.
All other aspects of Betamon are like Alphamon: the dice are predictable,
red wins after six turns, etc.

 * 
 */

public class TestBetamon {

	private Game game;

	@Before
	public void setup() {
		game = new StandardGame( new BetamonMoveStrategy(), new BetamonDieStrategy());
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
	 * newGame = playerInturn == Color.Black
	 */
	@Test
	public void testNewGame() {
		game.newGame();
		assertEquals(Color.NONE, game.getPlayerInTurn());
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

	/**
	 * Test of iterator(). Checking for: - Not null - Size = 28 - Location not
	 * null - Location order (correct index)
	 */
	@Test
	public void testIterator() {
		Iterator<Location> it = game.boardIterator();
		assertNotNull(it);
		int i = 0;
		while (it.hasNext()) {
			Location l = it.next();
			assertNotNull(l);
			i++;
		}
		assertEquals(i, 28);
	}

	/**
	 * Test of checkers. Checking for: - 30 checkers - Max 5 checkers at each
	 * spike-Location
	 */
	@Test
	public void testCheckers() {
		Iterator<Location> it = game.boardIterator();
		int i = 0;
		while (it.hasNext()) {
			Location l = it.next();
			i += game.getCount(l);
			if (l != Location.B_BAR && l != Location.R_BAR
					&& l != Location.B_BEAR_OFF && l != Location.R_BEAR_OFF)
				assertTrue(game.getCount(l) < 6);

		}
		assertEquals(i, 30);
	}

	/**
	 * Test of getColor(). Checking for: - Not null - Default colors
	 */
	@Test
	public void testColors() {
		Iterator<Location> it = game.boardIterator();
		while (it.hasNext()) {
			Location l = it.next();
			assertNotNull(game.getColor(l));
		}
		assertEquals(game.getColor(Location.B1), Color.RED);
		assertEquals(game.getColor(Location.R1), Color.BLACK);

		assertEquals(game.getColor(Location.B2), Color.NONE);
	}

	private void testDie(int[] d, int a, int b) {
		assertEquals(a, d[0]);
		assertEquals(b, d[1]);
	}

	private String atos(int[] arr) {
		String out = "";
		for (int i = 0; i < arr.length; i++) {
			out += arr[i] + "_";
		}
		return out;
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

	/**
	 *  4. A location with 2 or more opponent checkers is a blocked point and
      you are not allowed to move there.
	 */
	@Test
	public void moreThanOneCheckerOnToLocation() {
	    

	    assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        game.nextTurn();
        //to der gerne skulle vaere lovlige og stemme med terningerne 
        assertTrue(game.move(Location.R6, Location.R5));
        assertTrue(game.move(Location.R8, Location.R6));
        game.nextTurn();
        assertTrue(game.move(Location.R2, Location.R4));
        assertTrue(game.move(Location.R3, Location.R4));
        game.nextTurn();
        // der staar nu to sorte paa R4 
        assertFalse(game.move(Location.R6, Location.R4));


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
	    //et forkert
	    assertFalse(game.move(Location.B6, Location.B7));
	    //to der gerne skulle v;re lovlige og stemme med terningerne
	    assertTrue(game.move(Location.B6, Location.B5));
	    assertTrue(game.move(Location.B6, Location.B4));
	    game.nextTurn();
	    assertFalse(game.move(Location.R6, Location.R7));
        //to der gerne skulle v;re lovlige og stemme med terningerne
        assertTrue(game.move(Location.R6, Location.R5));
        assertTrue(game.move(Location.R6, Location.R4));
	    
	}

	@Test
	public void diceValuesLeft() {
		assertEquals(this.atos(new int[] { 2, 1 }), this.atos(game
				.diceValuesLeft()));
		assertTrue(game.move(Location.R1, Location.R3));
		assertEquals(this.atos(new int[] { 1 }), this.atos(game
				.diceValuesLeft()));
		assertTrue(game.move(Location.R1, Location.R2));
		assertEquals("", this.atos(game.diceValuesLeft()));

		game.nextTurn();

		assertEquals(this.atos(new int[] { 2, 1 }), this.atos(game
				.diceValuesLeft()));
		assertTrue(game.move(Location.B1, Location.B2));
		assertEquals(this.atos(new int[] { 2 }), this.atos(game
				.diceValuesLeft()));
		assertTrue(game.move(Location.B1, Location.B3));
		assertEquals("", this.atos(game.diceValuesLeft()));
	}

	
	/**
     * der er en på et felt move R1 R3 move R1 R4 nextTurn() move R6 R3 -> true
     * og black bar indeholder een brik
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
	
	

	/**
	 * This wrapper is only required for running the old JUnit 3.8 graphical
	 * user interface on new JUnit 4 test cases
	 */
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(TestBetamon.class);
	}
}
