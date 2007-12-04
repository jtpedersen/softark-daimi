package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for Alphamon game. Author: (c) Henrik B;rbak Christensen 2007
 */

public class TestBlocking {

    private Game game;

    @Before
    public void setup() {
        game = new StandardGame(new BlockedBoardFactory());
        game.newGame();
        game.nextTurn();
    }

    private class BlockedBoardFactory extends BetamonFactory {
        public Board createBoard() {
            return new BlockedBoardSetup();
        }
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

    @Test
    public void testFirstPlayer() {
        assertEquals(Color.BLACK, game.getPlayerInTurn());
    }

    @Test
    public void testBlackCanMove() {
        assertTrue(game.move(Location.B2, Location.B1));
        assertTrue(game.move(Location.B4, Location.B2));
        assertEquals(Color.NONE, game.getPlayerInTurn());

        assertEquals(0, game.getNumberOfMovesLeft());
        
        game.nextTurn();

        // there are no possible moves for red
        assertFalse(game.move(Location.R_BAR, Location.B1));
        assertFalse(game.move(Location.R_BAR, Location.B2));

        assertFalse(game.move(Location.R_BAR, Location.B7));
        assertFalse(game.move(Location.B1, Location.R_BAR));
        assertFalse(game.move(Location.R_BAR, Location.B1));
//        Helpers.showLocationCount(game, Color.RED);
//        Helpers.showLocationCount(game, Color.BLACK);
//        assertEquals(Color.NONE, game.getPlayerInTurn());
        // for(int i : game.diceValuesLeft())
        // System.out.println(i);
        assertEquals(0, game.getNumberOfMovesLeft());
    }

    private void testDie(int[] d, int a, int b) {
        assertEquals(a, d[0]);
        assertEquals(b, d[1]);
    }

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestBlocking.class);
    }
}
