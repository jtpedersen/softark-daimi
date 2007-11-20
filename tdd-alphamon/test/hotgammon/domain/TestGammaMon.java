package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
testCases for gammaMon primært {@link GammamonDieStrategy}

 * 
 */

public class TestGammaMon {

    private Game game;

    @Before
    public void setup() {
        game = new StandardGame( new GammamonFactory() );
        game.newGame();
        game.nextTurn();
    }

    /**
     * nextTurn() -> ny spiller
     * der stemmer med de terninger der er slået
     *  If die 1 > die 2 then red
     * starts; otherwise it is black that moves first.
     */
    @Test
    public void testNextPlayer() {
        int[] d = game.diceThrown();
        boolean blackStart = false;
        boolean redStart = false;
        int tries = 0;
        while (!blackStart || !redStart) {

//            showDice(d);
            
            if (d[0]>d[1]) {
                assertEquals(Color.RED, game.getPlayerInTurn());
                redStart = true;
            } else if (d[0]<d[1]){
                assertEquals(Color.BLACK, game.getPlayerInTurn());
                blackStart = true;
            }
            game.newGame();
            game.nextTurn();
            d = game.diceThrown();
            
            if (++tries>100)
                fail("The didnt both start in " + tries + "tries");
        }
    }
    
    private void showDice(int[] d){
        System.out.println("the dice");
        for(int i: d)
            System.out.println(i);
    }


    private void testDie(int[] d, int a, int b) {
        assertEquals(a, d[0]);
        assertEquals(b, d[1]);
    }

    private String atos(int[] arr) {
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            out += arr[i] + "_";
        }
        return out;
    }

    @Test
    public void numberOfMovesLeftBeforeMove() {
        assertEquals(2, game.getNumberOfMovesLeft());
    }

    /**
     * black vil always start in Betamon
     */
    @Test
    public void onlyStartPlayerMoves() {
        if (game.getPlayerInTurn() == Color.BLACK) {
            assertTrue(game.move(Location.R1, Location.R3));
            assertEquals(1, game.getCount(Location.R3));
            assertEquals(1, game.getCount(Location.R1));
        } else {
            assertTrue(game.move(Location.B1, Location.B3));
            assertEquals(1, game.getCount(Location.B3));
            assertEquals(1, game.getCount(Location.B1));
        }
    }

    @Test
    public void otherPlayerCantMove() {
        if (game.getPlayerInTurn() == Color.BLACK) {
            assertFalse(game.move(Location.R6, Location.R3));
            assertEquals(2, game.getCount(Location.R1));
        } else {
            assertFalse(game.move(Location.B6, Location.B3));
            assertEquals(2, game.getCount(Location.B1));
        }
    }

    /*
     *  4. A location with 2 or more opponent checkers is a blocked point and
      you are not allowed to move there.
     */
    @Test
    public void moreThanOneCheckerOnToLocation() {

        if (game.getPlayerInTurn() == Color.BLACK) {
            assertTrue(game.move(Location.R1, Location.R2));
            assertTrue(game.move(Location.R1, Location.R3));
            game.nextTurn();
            //to der gerne skulle vaere lovlige og stemme med terningerne 
            assertTrue(game.move(Location.R6, Location.R5));
            assertTrue(game.move(Location.R8, Location.R6));
            game.nextTurn();
            assertTrue(game.move(Location.R2, Location.R4));
            assertTrue(game.move(Location.R3, Location.R4));
            game.nextTurn();
            // der staar nu to sorte paa R4 
            assertFalse(game.move(Location.R6, Location.R4));
        } else {
            assertTrue(game.move(Location.B1, Location.B2));
            assertTrue(game.move(Location.B1, Location.B3));
            game.nextTurn();
            //to der gerne skulle vaere lovlige og stemme med terningerne 
            assertTrue(game.move(Location.B6, Location.B5));
            assertTrue(game.move(Location.B8, Location.B6));
            game.nextTurn();
            assertTrue(game.move(Location.B2, Location.B4));
            assertTrue(game.move(Location.B3, Location.B4));
            game.nextTurn();
            // der staar nu to roede paa B4 
            assertFalse(game.move(Location.B6, Location.B4));
        }
    }


    @Test
    public void numberOfMovesLeftAfterMove() {
        int movesLeft = game.getNumberOfMovesLeft();
        if (game.getPlayerInTurn() == Color.BLACK) {
            game.move(Location.R1, Location.R3);
            assertEquals(movesLeft-1, game.getNumberOfMovesLeft());
            game.move(Location.R1, Location.R2);
            assertEquals(movesLeft-2, game.getNumberOfMovesLeft());
        } else {
            assertTrue( game.move(Location.B1, Location.B3) );
            assertEquals(movesLeft-1, game.getNumberOfMovesLeft());
            game.move(Location.B1, Location.B2);
            assertEquals(movesLeft-2, game.getNumberOfMovesLeft());
        }
    }

    @Test
    public void getPlayerInTurnWithNoMoves() {
        if (game.getPlayerInTurn() == Color.BLACK) {
            while(game.getNumberOfMovesLeft() > 0) {
                assertTrue(game.move(Location.B6, Location.B5));
            }
            assertEquals(Color.NONE, game.getPlayerInTurn());
        } else {
            while(game.getNumberOfMovesLeft() > 0) {
                assertTrue(game.move(Location.R6, Location.R5));
            }
            assertEquals(Color.NONE, game.getPlayerInTurn());
        }
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
        if (game.getPlayerInTurn() == Color.BLACK) {
            assertTrue(game.move(Location.R1, Location.R3));
            assertTrue(game.move(Location.R1, Location.R2));
            assertFalse(game.move(Location.R3, Location.R4));
        } else {
            assertTrue(game.move(Location.B1, Location.B3));
            assertTrue(game.move(Location.B1, Location.B2));
            assertFalse(game.move(Location.B3, Location.B4));
        }
    }

    /* Dont apply to gammamon, only betamon
    @Test
    public void onlyCorrectDirection() {
        //et forkert
        assertFalse(game.move(Location.B6, Location.B7));
        //to der gerne skulle v;re lovlige og stemme med terningerne
        assertTrue(game.move(Location.B6, Location.B5));
        assertTrue(game.move(Location.B6, Location.B4));
        game.nextTurn();
        assertFalse(game.move(Location.R6, Location.R7));
        //to der gerne skulle v;re lovlige og stemme med terningerne
        assertTrue(game.move(Location.R6, Location.R5));
        assertTrue(game.move(Location.R6, Location.R4));

    }
    */

    /* Dont apply to gammamon, only betamon
    @Test
    public void diceValuesLeft() {
        assertEquals(this.atos(new int[] { 2, 1 }), this.atos(game
                .diceValuesLeft()));
        assertTrue(game.move(Location.R1, Location.R3));
        assertEquals(this.atos(new int[] { 1 }), this.atos(game
                .diceValuesLeft()));
        assertTrue(game.move(Location.R1, Location.R2));
        assertEquals("", this.atos(game.diceValuesLeft()));

        game.nextTurn();

        assertEquals(this.atos(new int[] { 2, 1 }), this.atos(game
                .diceValuesLeft()));
        assertTrue(game.move(Location.B1, Location.B2));
        assertEquals(this.atos(new int[] { 2 }), this.atos(game
                .diceValuesLeft()));
        assertTrue(game.move(Location.B1, Location.B3));
        assertEquals("", this.atos(game.diceValuesLeft()));
    }
    */

    /**
     * der er en på et felt move R1 R3 move R1 R4 nextTurn() move R6 R3 -> true
     * og black bar indeholder een brik, blot
     */
    /* ?
    @Test
    public void onlyOneChekerAtMoveToLocation() {
        if (game.getPlayerInTurn() == Color.BLACK) {
            game.move(Location.R1, Location.R3);
            game.move(Location.R3, Location.R4);
            game.nextTurn();
            assertTrue(game.move(Location.R6, Location.R4));
            assertEquals(1, game.getCount(Location.B_BAR));
            assertEquals(Color.RED, game.getColor(Location.R4));
        } else {
            game.move(Location.R1, Location.R3);
            game.move(Location.R3, Location.R4);
            game.nextTurn();
            assertTrue(game.move(Location.R6, Location.R4));
            assertEquals(1, game.getCount(Location.B_BAR));
            assertEquals(Color.RED, game.getColor(Location.R4));
        }
    }
    */

    /* really needed?
    @Test 
    public void bearOffTest() {
        assertTrue(game.move(Location.B6, Location.B4));
        assertTrue(game.move(Location.B4, Location.B3));
        game.nextTurn();
        assertTrue(game.move(Location.R6, Location.R4));
        assertTrue(game.move(Location.R4, Location.R3));
        game.nextTurn();
        assertTrue(game.move(Location.B3, Location.B2));
        assertTrue(game.move(Location.B2, Location.B_BEAR_OFF));
        game.nextTurn();
        assertTrue(game.move(Location.R3, Location.R2));
        assertTrue(game.move(Location.R2, Location.R_BEAR_OFF));
    }
    */

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestGammaMon.class);
    }
}
