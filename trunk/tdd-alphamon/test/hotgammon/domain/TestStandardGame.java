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

public class TestStandardGame {

    private Game game;

    @Before
    public void setup() {
        
        //to "simple" strategier
        MonFactory factory = new SimpleFactory();
        game = new StandardGame( factory );
        game.newGame();
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
    private void testDie(int[] d, int a, int b) {
        assertEquals(a, d[0]);
        assertEquals(b, d[1]);
    }
    
    public void testDiceThrown() {
        testDie(game.diceThrown(), 1, 2);
     
    }
    /**
     * nextTurn() -> ny spiller
     * (sort starter altid)
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
     * black vil always start 
    assumes standard positions
     */
    @Test
    public void movement() {
        Location from  = Location.R1;
        Location to = Location.R3;
        assertTrue(game.move(from, to));
        assertEquals(1, game.getCount(to));
        assertEquals(1, game.getCount(from));
        assertTrue(game.getColor(from) == game.getColor(to));
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


    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestStandardGame.class);
    }
}
