package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for Alphamon game. Author: (c) Henrik B;rbak Christensen 2007
 */

public class TestDeltaMon {

    private Game game;

    @Before public void setup() {
        game = new StandardGame( new DeltamonFactory() );
        //alpham,on dicefactory
        game.newGame();
        game.nextTurn();
    }

    @Test public void ShouldFailWhenMoveWrongDirection() {
        assertFalse( game.move( Location.B6, Location.B9 ));
        assertTrue( game.move( Location.B6, Location.B2 ));
        game.nextTurn();
        assertFalse( game.move( Location.R6, Location.R9 ));
        assertTrue( game.move( Location.R6, Location.R2 ));
    }

    @Test public void ShouldWinWhenAllInInnerField() {
        assertEquals( Color.NONE, game.winner() );
        assertTrue( game.move( Location.R1, Location.B2 ));
        assertTrue( game.move( Location.R1, Location.B2 ));
        game.nextTurn(); game.nextTurn();
     
        assertEquals( Color.NONE, game.winner() );
        assertTrue( game.move( Location.R12, Location.B3 ));
        assertTrue( game.move( Location.R12, Location.B3 ));
        game.nextTurn(); game.nextTurn();
        assertTrue( game.move( Location.R12, Location.B3 ));
        assertTrue( game.move( Location.R12, Location.B3 ));
        game.nextTurn(); game.nextTurn();
        assertTrue( game.move( Location.R12, Location.B3 ));

        assertEquals( Color.NONE, game.winner() );
        assertTrue( game.move( Location.B8, Location.B4 ));
        
        
        game.nextTurn(); game.nextTurn();
        assertTrue( game.move( Location.B8, Location.B4 ));
        assertTrue( game.move( Location.B8, Location.B4 ));
        
     

        
        assertEquals( Color.BLACK, game.winner() );
    }

    
    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestDeltaMon.class);
    }
}
