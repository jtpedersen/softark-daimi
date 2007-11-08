package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for Alphamon game. Author: (c) Henrik B;rbak Christensen 2007
 */

public class TestAlphamon {

    private Game game;

    @Before public void setup() {
	game = new AlphamonGame();
	game.newGame();
	game.nextTurn();
    }

    /**
     * the dice rolls are not random. The first two dice rolls give [1,2], the
     * next two [3,4], next [5,6], and then it starts all over again.
     */
    @Test public void testDiceThrown() {
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
	    assertEquals(l.getIndex(), i);
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
	for(int i=0; i<arr.length; i++) {
	    out += arr[i]+"_";
	}
	return out;
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
	public void diceValuesLeft() {
	assertEquals( this.atos(new int[] {2,1,0,0}), this.atos(game.diceValuesLeft()) );
	assertTrue( game.move(Location.R1, Location.R3) );
	assertEquals( this.atos(new int[] {1,0,0,0}), this.atos(game.diceValuesLeft()) );
	assertTrue( game.move(Location.R1, Location.R2) );
	assertEquals( this.atos(new int[] {0,0,0,0}), this.atos(game.diceValuesLeft()) );

	game.nextTurn();

	assertEquals( this.atos(new int[] {2,1,0,0}), this.atos(game.diceValuesLeft()) );
	assertTrue( game.move(Location.B1, Location.B2) );
	assertEquals( this.atos(new int[] {2,0,0,0}), this.atos(game.diceValuesLeft()) );
	assertTrue( game.move(Location.B1, Location.B3) );
	assertEquals( this.atos(new int[] {0,0,0,0}), this.atos(game.diceValuesLeft()) );
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
	assertTrue( game.move(Location.R1, Location.R3) );
	assertTrue( game.move(Location.R1, Location.R2) );
	assertEquals( Color.NONE, game.getPlayerInTurn() );
    }

    @Test
	public void winner() {
	assertEquals( Color.NONE, game.winner() ); 
	game.nextTurn();
	assertEquals( Color.NONE, game.winner() ); 
	game.nextTurn();
	assertEquals( Color.NONE, game.winner() ); 
	game.nextTurn();
	assertEquals( Color.NONE, game.winner() ); 
	game.nextTurn();
	assertEquals( Color.NONE, game.winner() ); 
	game.nextTurn();
	assertEquals( Color.RED, game.winner() ); 
    }

    @Test
	public void moveWhenNoMoreMoves() {
	assertTrue( game.move(Location.R1, Location.R3) );
	assertTrue( game.move(Location.R1, Location.R2) );
	assertFalse( game.move(Location.R3, Location.R4) );
    }

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
	return new junit.framework.JUnit4TestAdapter(TestAlphamon.class);
    }
}
