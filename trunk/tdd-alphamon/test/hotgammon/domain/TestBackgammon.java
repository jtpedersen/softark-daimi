package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for intehgration for semimon
 * 
 */

public class TestBackgammon {

    private Game game;

    @Before
    public void setup() {
        game = new StandardGame(new SemimonFactory());
        game.newGame();
        game.nextTurn();
    }

    @Test
    public void removesCorrectDice() {
        // return new SequenceDieStrategy(new int[] {1,2}); det er en test med
        // fast terning slag
        game = new StandardGame(new SemiTestSemimonFactory());
        game.newGame();
        game.nextTurn();

        assertEquals(this.atos(new int[] { 2, 1 }),
                this.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.R1, Location.R3));

        assertEquals(this.atos(new int[] { 1 }),
                this.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.R1, Location.R2));

        game.nextTurn();

        assertEquals(this.atos(new int[] { 2, 1 }),
                this.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.B1, Location.B2));

        assertEquals(this.atos(new int[] { 2 }),
                this.atos(game.diceValuesLeft()));
        assertTrue(game.move(Location.B1, Location.B3));

    }

    private String atos(int[] arr) {
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            out += arr[i] + "_";
        }
        return out;
    }

    private class SemiTestSemimonFactory extends SemimonFactory {
        public DieStrategy createDieStrategy() {
            return new SequenceDieStrategy(new int[] { 1, 2 });
        }
    }

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestBackgammon.class);
    }

}
