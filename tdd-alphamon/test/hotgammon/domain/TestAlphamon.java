package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for Alphamon game. Author: (c) Henrik B;rbak Christensen 2007
 */

public class TestAlphamon {

	private Game game;

	@Before
	public void setup() {
		game = new StandardGame( new AlphamonFactory() );
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
//    for(int i: game.diceValuesLeft())
//        System.out.println(i);
//    System.out.println(game.getNumberOfMovesLeft());
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
     * black vil always start in alphamon
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

	@Test
	public void moreThanOneCheckerOnToLocation() {
		assertFalse(game.move(Location.R1, Location.R6));
	}


	@Test
	public void numberOfMovesLeftAfterMove() {
		assertTrue( game.move(Location.R1, Location.R3) );
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

	/**
	 * This wrapper is only required for running the old JUnit 3.8 graphical
	 * user interface on new JUnit 4 test cases
	 */
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(TestAlphamon.class);
	}
}
