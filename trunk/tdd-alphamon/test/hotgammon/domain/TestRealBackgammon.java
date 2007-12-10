package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hotgammon.AI.BoardConfiguration;

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
        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.B1, Color.BLACK, 5),
                new BoardConfiguration(Location.B2, Color.BLACK, 5),
                new BoardConfiguration(Location.B3, Color.BLACK, 3),
                new BoardConfiguration(Location.B7, Color.BLACK, 2),

                new BoardConfiguration(Location.R1, Color.RED, 5),
                new BoardConfiguration(Location.R8, Color.RED, 5),
                new BoardConfiguration(Location.R9, Color.RED, 5) };

        game = new StandardGame(new MonTestFactory(config, new int[] { 1, 2 }));
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
        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.B_BEAR_OFF, Color.BLACK, 14),
                new BoardConfiguration(Location.B2, Color.BLACK, 1),

                new BoardConfiguration(Location.R1, Color.RED, 5),
                new BoardConfiguration(Location.R8, Color.RED, 5),
                new BoardConfiguration(Location.R9, Color.RED, 5) };

        game = new StandardGame(new MonTestFactory(config, new int[] { 1, 2 }));
        game.newGame();
        game.nextTurn();
        assertEquals(Color.NONE, game.winner());
        assertTrue(game.move(Location.B2, Location.B_BEAR_OFF));
        assertEquals(Color.BLACK, game.winner());

    }

    @Test
    public void AssureBearOffWorks() {
        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.R2, Color.RED, 1),
                new BoardConfiguration(Location.R3, Color.RED, 2),
                new BoardConfiguration(Location.R_BEAR_OFF, Color.RED, 12),

        };
        game = new StandardGame(new MonTestFactory(new RealBackgammonFactory(),
                config, new int[] { 5, 2, 1, 1, 4, 1 }));
        game.newGame();
        game.nextTurn();

        assertEquals(Color.NONE, game.winner());
        assertEquals(Color.RED, game.getPlayerInTurn());
        assertEquals(Color.NONE, game.getColor(Location.B3));

        // Helpers.showLocationCount(game, Color.RED);
        // Helpers.showDice(game.diceValuesLeft());

        assertTrue(game.move(Location.R2, Location.R_BEAR_OFF));
        assertEquals(Helpers.atos(new int[] { 5 }),
                Helpers.atos(game.diceValuesLeft()));
//         Helpers.showLocationCount(game, Color.RED);
//         Helpers.showDice(game.diceValuesLeft());
        //        
        assertEquals(Color.RED, game.getPlayerInTurn());
        assertTrue(game.move(Location.R3, Location.R_BEAR_OFF));
        assertEquals(0, game.getNumberOfMovesLeft());
        assertEquals(Color.NONE, game.getPlayerInTurn());

        // wrapping around the board we do not like it
        assertFalse(game.move(Location.B3, Location.R_BEAR_OFF));

        // Helpers.showLocationCount(game, Color.RED);
        // Helpers.showDice(game.diceValuesLeft());
        //        
        // stupid bug that allowed movement to bar.
        assertFalse(game.move(Location.R2, Location.B_BAR));
        assertFalse(game.move(Location.R3, Location.R_BEAR_OFF));
        assertEquals(0, game.getNumberOfMovesLeft());
        game.nextTurn();
        assertEquals(Color.NONE, game.getPlayerInTurn());
        game.nextTurn();

        assertTrue(game.move(Location.R3, Location.R_BEAR_OFF));
        assertEquals(Color.RED, game.winner());

    }

    @Test
    public void assureBearOffOnlyForHighLocation() {
        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.R1, Color.RED, 1),
                new BoardConfiguration(Location.R2, Color.RED, 1),
                new BoardConfiguration(Location.R3, Color.RED, 1),
                new BoardConfiguration(Location.R_BEAR_OFF, Color.RED, 12),

        };
        game = new StandardGame(new MonTestFactory(new RealBackgammonFactory(),
                config, new int[] { 5, 4 }));
        game.newGame();
        game.nextTurn();
        assertEquals(Color.NONE, game.winner());
        assertEquals(Color.RED, game.getPlayerInTurn());

        assertFalse(game.move(Location.R1, Location.R_BEAR_OFF));
        assertFalse(game.move(Location.R2, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.R3, Location.R_BEAR_OFF));
        assertFalse(game.move(Location.R1, Location.R_BEAR_OFF));
        assertTrue(game.move(Location.R2, Location.R_BEAR_OFF));
    }

    @Test
    public void AssureNoBearOffWithCheckersInBar() {

        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.B_BEAR_OFF, Color.BLACK, 5),
                new BoardConfiguration(Location.B2, Color.BLACK, 5),
                new BoardConfiguration(Location.B3, Color.BLACK, 3),
                new BoardConfiguration(Location.B_BAR, Color.BLACK, 1),

                new BoardConfiguration(Location.R7, Color.RED, 5),
                new BoardConfiguration(Location.R8, Color.RED, 5),
                new BoardConfiguration(Location.R9, Color.RED, 5) };

        game = new StandardGame(new MonTestFactory(config, new int[] { 1, 2 }));
        game.newGame();
        game.nextTurn();
        assertFalse(game.move(Location.B1, Location.B_BEAR_OFF));
        assertFalse(game.move(Location.B2, Location.B_BEAR_OFF));

        assertTrue(game.move(Location.B_BAR, Location.R1));
        // System.out.println(Helpers.showBoard(game));
        // Helpers.showDice(game.diceValuesLeft());
        assertEquals(0, game.getCount(Location.B_BAR));
        assertFalse(game.move(Location.B2, Location.B_BEAR_OFF));
        assertTrue(game.move(Location.R1, Location.R3));

    }

    @Test
    public void AssureBearOffLotsOffCheckers() {

        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.B1, Color.BLACK, 5),
                new BoardConfiguration(Location.B2, Color.BLACK, 5),
                new BoardConfiguration(Location.B3, Color.BLACK, 5),
                new BoardConfiguration(Location.R1, Color.RED, 5),
                new BoardConfiguration(Location.R2, Color.RED, 5),
                new BoardConfiguration(Location.R3, Color.RED, 5), 
                };

        game = new StandardGame(new MonTestFactory(config, new int[] { 1, 2 }));
        
        game.newGame();
//        System.out.println("bear off testing");
//        System.out.println(Helpers.showBoard(game));
        assertEquals(5, game.getCount(Location.B1));
        assertEquals(5, game.getCount(Location.R1));
        game.nextTurn();
        assertEquals(2, game.getNumberOfMovesLeft());
        
        
        assertEquals(Color.NONE, game.winner());
        assertEquals(Color.BLACK, game.getPlayerInTurn());
        assertTrue(game.move(Location.B1, Location.B_BEAR_OFF));
        
        assertTrue(game.move(Location.B2, Location.B_BEAR_OFF));
        game.nextTurn();
//        Helpers.showDice(game.diceValuesLeft());
        assertTrue(game.move(Location.R1, Location.R_BEAR_OFF));
//        Helpers.showDice(game.diceValuesLeft());
        assertTrue(game.move(Location.R2, Location.R_BEAR_OFF));

    }

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestSemimon.class);
    }

}
