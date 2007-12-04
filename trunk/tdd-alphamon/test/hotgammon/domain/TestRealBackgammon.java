package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for intehgration for semimon
 * 
 */

public class TestRealBackgammon {

    private Game game;

    @Before
    public void setup() {
        game = new StandardGame(new RealBackgammonFactory());
        game.newGame();
        game.nextTurn();
    }

    @Test
    public void AssureOnlyToBearOffWhenAllInInnerField() {
        // return new SequenceDieStrategy(new int[] {1,2}); det er en test med
        // fast terning slag
        game = new StandardGame(new TestRealBackGammonFactory());
        game.newGame();
        game.nextTurn();

        assertFalse(game.move(Location.B1, Location.B_BEAR_OFF));
        assertFalse(game.move(Location.B2, Location.B_BEAR_OFF));

        assertTrue(game.move(Location.B7, Location.B6));
        assertTrue(game.move(Location.B7, Location.B5));
        assertEquals(Color.NONE, game.winner());
        game.nextTurn();
        assertEquals(Color.NONE, game.winner());

    }

    @Test
    public void AssureBlackeWinsWith15lInBearOff() {
        // return new SequenceDieStrategy(new int[] {1,2}); det er en test med
        // fast terning slag
        game = new StandardGame(new RealBackgammonWinningFactory());
        game.newGame();
        game.nextTurn();
        assertEquals(Color.NONE, game.winner());
        assertTrue(game.move(Location.B2, Location.B_BEAR_OFF));
        assertEquals(Color.BLACK, game.winner());

    }

    private class TestRealBackGammonFactory extends RealBackgammonFactory {
        public DieStrategy createDieStrategy() {
            return new SequenceDieStrategy(new int[] { 1, 2 });
        }
        
        
        public Board createBoard() {
            BoardConfiguration[] config = new BoardConfiguration[] {
                    new BoardConfiguration(Location.B1,Color.BLACK,5),
                    new BoardConfiguration(Location.B2,Color.BLACK,5),
                    new BoardConfiguration(Location.B3,Color.BLACK,3),
                    new BoardConfiguration(Location.B7,Color.BLACK,2),
                    
                    new BoardConfiguration(Location.R1,Color.RED,5),
                    new BoardConfiguration(Location.R8,Color.RED,5),
                    new BoardConfiguration(Location.R9,Color.RED,5)};
            return new FixedBoardSetup(config);
        }
    }

    private class RealBackgammonWinningFactory extends RealBackgammonFactory {
        public DieStrategy createDieStrategy() {
            return new SequenceDieStrategy(new int[] { 1, 2 });
        }

        public Board createBoard() {
            BoardConfiguration[] config = new BoardConfiguration[] {
                    new BoardConfiguration(Location.B_BEAR_OFF, Color.BLACK, 14),
                    new BoardConfiguration(Location.B2, Color.BLACK, 1),

                    new BoardConfiguration(Location.R1, Color.RED, 5),
                    new BoardConfiguration(Location.R8, Color.RED, 5),
                    new BoardConfiguration(Location.R9, Color.RED, 5) };
            return new FixedBoardSetup(config);
        }
    }

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestSemimon.class);
    }

}
