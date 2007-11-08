package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/** Testcases for Backgammon Board.
Author: (c) Henrik Brbak Christensen 2007
*/

public class TestBoard {

    private Board board;

    @Before public void setUp() { 
        board = new StandardBoard();
    }
    
    /** Test of iterator(). Checking for:
     *  - Not null
     *  - Size = 28
     *  - Location not null
     *  - Location order (correct index)
     */
    @Test public void testIterator() {
        Iterator<Location> it = board.iterator();
        assertNotNull(it);
        int i = 0;
        while(it.hasNext())
        {
            Location l = it.next();
            assertNotNull(l);
//            assertEquals(i, l.getIndex()); indexer passer ikke hvis man bare putter hele enum i en arraylist
            i++;
        }
        assertEquals(i,28);
    }

    /** Test of checkers. Checking for:
     *  - 30 checkers
     *  - Max 5 checkers at each spike-Location
     */
    @Test public void testCheckers() {
        Iterator<Location> it = board.iterator();
        int i = 0;
        while(it.hasNext())
        {
            Location l = it.next();
            i+= board.getCount(l);
            if(l != Location.B_BAR && l != Location.R_BAR 
                    && l != Location.B_BEAR_OFF && l != Location.R_BEAR_OFF)
                assertTrue(board.getCount(l) < 6);

        }
        assertEquals(i,30);
    }

    /** Test of getColor(). Checking for:
     *  - Not null
     *  - Default colors
     */
    @Test public void testColors() {
        Iterator<Location> it = board.iterator();
        while(it.hasNext())
        {
            Location l = it.next();
            assertNotNull(board.getColor(l));
        }
        assertEquals(board.getColor(Location.B1),Color.RED);
        assertEquals(board.getColor(Location.R1),Color.BLACK);
        assertEquals(board.getColor(Location.B2),Color.NONE);

    }

    @Test public void testMove() {
        board.move(Location.R6,Location.R5);
        assertEquals(board.getCount(Location.R5),1);
        assertEquals(board.getCount(Location.R6),4);
        assertEquals(board.getColor(Location.R5),board.getColor(Location.R6));
        board.move(Location.R5,Location.R6);
        assertEquals(board.getCount(Location.R6),5);
        assertEquals(board.getCount(Location.R5),0);
        assertEquals(board.getColor(Location.R5),Color.NONE);
    }

    @Test public void testReset() {
        board.move(Location.R6,Location.R5);
        board.reset();
        assertEquals(board.getCount(Location.R5),0);
        assertEquals(board.getColor(Location.R5),Color.NONE);
    }

    /** This wrapper is only required for running the old JUnit 3.8
     * graphical user interface on new JUnit 4 test cases */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestBoard.class);
    } 
}
