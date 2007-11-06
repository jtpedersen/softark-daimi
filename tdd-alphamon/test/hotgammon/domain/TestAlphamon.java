package hotgammon.domain;

import static org.junit.Assert.assertEquals;
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

	@Before
	public void setup() {
		game = new AlphamonGame();
	}

	/**
	 * the dice rolls are not random. The first two dice rolls give [1,2], the
	 * next two [3,4], next [5,6], and then it starts all over again.
	 */
	@Test
	public void testDiceThrown() {

		int[] d = game.diceThrown();
		testDie(d, 1, 2);

		d = game.diceThrown();
		testDie(d, 3, 4);

		d = game.diceThrown();
		testDie(d, 3, 4);

		d = game.diceThrown();
		testDie(d, 5, 6);

		d = game.diceThrown();
		testDie(d, 1, 2);

		d = game.diceThrown();
		testDie(d, 3, 4);

		d = game.diceThrown();
		testDie(d, 3, 4);

		d = game.diceThrown();
		testDie(d, 5, 6);
	}

	/**
	 * newGame = playerInturn == Color.Black
	 */
	@Test
	public void testNewGame() {
		game.newGame();
		assertEquals(Color.BLACK, game.getPlayerInTurn());
	}

	/**
	 * nextTurn() -> ny spiller
	 */
	@Test
	public void testNextPlayer() {
		game.newGame();
		game.nextTurn();
		assertEquals(Color.RED, game.getPlayerInTurn());
	}
	
	   /** Test of iterator(). Checking for:
     *  - Not null
     *  - Size = 28
     *  - Location not null
     *  - Location order (correct index)
     */
    @Test public void testIterator() {
        Iterator<Location> it = game.boardIterator();
        assertNotNull(it);
        int i = 0;
        while(it.hasNext())
        {
            Location l = it.next();
            assertNotNull(l);
            assertEquals(l.getIndex(),i);
            i++;
        }
        assertEquals(i,28);
    }

    /** Test of checkers. Checking for:
     *  - 30 checkers
     *  - Max 5 checkers at each spike-Location
     */
    @Test public void testCheckers() {
        Iterator<Location> it = game.boardIterator();
        int i = 0;
        while(it.hasNext())
        {
            Location l = it.next();
            i+= game.getCount(l);
            if(l != Location.B_BAR && l != Location.R_BAR 
                    && l != Location.B_BEAR_OFF && l != Location.R_BEAR_OFF)
                assertTrue(game.getCount(l) < 6);

        }
        assertEquals(i,30);
    }

    /** Test of getColor(). Checking for:
     *  - Not null
     *  - Default colors
     */
    @Test public void testColors() {
        Iterator<Location> it = game.boardIterator();
        while(it.hasNext())
        {
            Location l = it.next();
            assertNotNull(game.getColor(l));
        }
        assertEquals(game.getColor(Location.B1),Color.RED);
        assertEquals(game.getColor(Location.R1),Color.BLACK);
        
        assertEquals(game.getColor(Location.B2),Color.NONE);
    }

	private void testDie(int[] d, int a, int b) {
		assertEquals(d[0], a);
		assertEquals(d[1], b);
	}

	@Test
	public void testStart() {
		assertTrue(true);
	}

	/**
	 * This wrapper is only required for running the old JUnit 3.8 graphical
	 * user interface on new JUnit 4 test cases
	 */
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(TestAlphamon.class);
	}
}
