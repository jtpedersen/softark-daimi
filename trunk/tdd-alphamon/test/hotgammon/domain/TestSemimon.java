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

public class TestSemimon {

    private Game game;

    @Before
	public void setup() {
	game = new StandardGame( new SemimonFactory() );
	game.newGame();
	game.nextTurn();
    }

        @Test
	public void blah() {
	assertEquals(true, false);
	
        }	
	
    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
	return new junit.framework.JUnit4TestAdapter(TestSemimon.class);
    }
}
